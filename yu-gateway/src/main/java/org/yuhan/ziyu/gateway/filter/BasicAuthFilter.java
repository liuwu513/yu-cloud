package org.yuhan.ziyu.gateway.filter;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.yuhan.ziyu.base.authserver.dto.AuthInfoDto;
import org.yuhan.ziyu.base.authserver.dto.PermissionDto;
import org.yuhan.ziyu.base.common.AuthConstant;
import org.yuhan.ziyu.base.common.SecurityConstant;
import org.yuhan.ziyu.gateway.common.FilterConstants;
import org.yuhan.ziyu.gateway.common.result.ReturnMessage;
import org.yuhan.ziyu.gateway.common.util.RequestContextUtil;
import org.yuhan.ziyu.gateway.common.util.ResponseUtil;
import org.yuhan.ziyu.gateway.config.IgnoredUrlsProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <pre>
 * 描述：鉴权信息过滤
 * </pre>
 *
 * @author liuwu
 * @version v2.0 2017年7月24日 下午4:45:55
 *          <p>
 * <pre>
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class BasicAuthFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(BasicAuthFilter.class);

    /**
     * 配置鉴权中心路由过滤，鉴权中心自行鉴权即可
     */
    @Value("${api.routes.authserver.serviceId}")
    private String authRoutePath;

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        boolean postMethod = StringUtils.equalsIgnoreCase(req.getMethod(), "post");
        //仅支持post请求,并过滤掉白名单配置
        if (postMethod) {
            String requestURI = getRequestPath(ctx);
            //忽略访问地址权限控制
            for (String path : ignoredUrlsProperties.getUrls()) {
                if (requestURI.matches(path.replace("**", "*"))) {
                    return false;
                }
            }
        }
        return postMethod;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_FILTER;
    }

    @Override
    public int filterOrder() {
        return 6;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String authToken = RequestContextUtil.getAuthToken(ctx);

        log.info(ctx.getRequest().getContentType());

        if (isAuthRoute(ctx)) {
            // Disable default zuul error Filter
            ctx.set("sendErrorFilter.ran", true);
            return null;
        }

        String requestURI = getRequestPath(ctx);
        log.info("路由请求地址：{}", requestURI);


        if (StringUtils.isBlank(authToken)) {
            log.debug("Token is empty");
            RequestContextUtil.setResponseUnauthorized(ctx, ReturnMessage.UNAUTHORIZED_TOKEN_MSG);
            return null;
        }

        //透传cookie auth-token值到下游服务
        //ctx.addZuulRequestHeader("Cookie", "auth-token="+authToken);

        // 解析token
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SecurityConstant.JWT_SIGN_KEY)
                    .parseClaimsJws(authToken.replace(SecurityConstant.TOKEN_SPLIT, ""))
                    .getBody();

            //获取用户名
            String username = claims.getSubject();
            Map<String, String> permsMap = getPermsMap();

            //设置鉴权请求信息
            setAuthRequest(ctx, claims);

            if (isExistsPerms(requestURI, permsMap)) {
                String needPerm = MapUtils.getString(permsMap, requestURI);

                //获取权限
                String authority = claims.get(SecurityConstant.AUTHORITIES).toString();

                if (StringUtils.isNotBlank(authority)) {
                    List<String> list = new Gson().fromJson(authority, new TypeToken<List<String>>() {
                    }.getType());

                    //若存在访问地址在权限中，则直接跳过
                    if (list.contains(needPerm)) {
                        // Disable default zuul error Filter
                        ctx.set("sendErrorFilter.ran", true);
                        return null;
                    } else {
                        log.info("用户无权访问地址:{}", requestURI);
                        ResponseUtil.out(ctx.getResponse(), ResponseUtil.resultMap(false, 500, ReturnMessage.SC_EXPRIED_MSG));
                        ctx.setSendZuulResponse(false);
                        return null;
                    }
                }
            }
        } catch (ExpiredJwtException e) {
            log.info("用户登录token已过期 : " + authToken);
            ResponseUtil.out(ctx.getResponse(), ResponseUtil.resultMap(false, 500, ReturnMessage.SC_EXPRIED_MSG));
            ctx.setSendZuulResponse(false);
            return null;
        } catch (Exception e) {
            log.error("解析token错误：", e);
            ResponseUtil.out(ctx.getResponse(), ResponseUtil.resultMap(false, 500, "解析token错误"));
            ctx.setSendZuulResponse(false);
            return null;
        }
        // Disable default zuul error Filter
        ctx.set("sendErrorFilter.ran", true);
        return null;
    }

    /**
     * 请求URI地址，过滤掉路由名称
     *
     * @param ctx
     * @return
     */
    private String getRequestPath(RequestContext ctx) {
        return (String) ctx.get("requestURI");
    }

    /**
     * 判断是否为鉴权服务
     *
     * @param ctx
     * @return
     */
    private Boolean isAuthRoute(RequestContext ctx) {
        String serviceId = (String) ctx.get("serviceId");
        if (StringUtils.equals(serviceId, authRoutePath)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    private void setAuthRequest(RequestContext ctx, Claims claims) throws IOException {
        String authInfo = claims.get(SecurityConstant.AUTH_INFO_KEY).toString();
        if (StringUtils.isNotBlank(authInfo)) {
            AuthInfoDto authInfoDto = new Gson().fromJson(authInfo, new TypeToken<AuthInfoDto>() {
            }.getType());

            //获取实体信息
            InputStream in = (InputStream) ctx.get("requestEntity");
            if (in == null) {
                in = ctx.getRequest().getInputStream();
            }
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            if (StringUtils.isNotBlank(body)) {
                JsonElement jsonElement = new JsonParser().parse(body);
                JsonObject requestBody = (JsonObject) jsonElement;

                //修改鉴权信息
                requestBody.addProperty("userId", authInfoDto.getUserId());
                requestBody.addProperty("userName", authInfoDto.getUserName());
                requestBody.addProperty("dataScope", authInfoDto.getDataScope());
                requestBody.addProperty("departmentId", authInfoDto.getDepartmentId());

                log.info("鉴权信息：{}", authInfoDto.toString());
                if (CollectionUtils.isNotEmpty(authInfoDto.getDepartmentIds())) {
                    requestBody.addProperty("departmentIds", StringUtils.join(authInfoDto.getDepartmentIds(), ","));
                }
                byte[] reqBodyBytes = requestBody.toString().getBytes("UTF-8");
                ctx.setRequest(new HttpServletRequestWrapper(ctx.getRequest()) {
                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ServletInputStreamWrapper(reqBodyBytes);
                    }

                    @Override
                    public int getContentLength() {
                        return reqBodyBytes.length;
                    }

                    @Override
                    public long getContentLengthLong() {
                        return reqBodyBytes.length;
                    }
                });
            }
        }
    }


    /**
     * 路径是否存在于权限控制中，如若存在则返回True,不存在返回false
     *
     * @param requestURI
     * @param permsMap
     * @return
     */
    private Boolean isExistsPerms(String requestURI, Map<String, String> permsMap) {
        if (!permsMap.keySet().contains(requestURI)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    /**
     * 获取所有权限 Mapping
     *
     * @return
     */
    private Map<String, String> getPermsMap() {
        List<PermissionDto> permissionDtoList = (List<PermissionDto>) redisTemplate.opsForValue().get(AuthConstant.CACHE_AUTH_PERMS);
        if (CollectionUtils.isNotEmpty(permissionDtoList)) {
            return permissionDtoList.stream().collect(Collectors.toMap(item -> item.getPath(), item -> item.getTitle()));
        }
        return Maps.newHashMap();
    }
}
