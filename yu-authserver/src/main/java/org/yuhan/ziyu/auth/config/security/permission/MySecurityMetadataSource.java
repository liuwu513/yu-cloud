package org.yuhan.ziyu.auth.config.security.permission;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.yuhan.ziyu.auth.config.redis.RedisCacheDao;
import org.yuhan.ziyu.auth.entity.Permission;
import org.yuhan.ziyu.auth.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.yuhan.ziyu.base.authserver.dto.PermissionDto;
import org.yuhan.ziyu.base.common.AuthConstant;
import org.yuhan.ziyu.base.common.CommonConstant;

import java.util.*;

/**
 * 权限资源管理器
 * 为权限决断器提供支持
 * @author Howell
 */
@Slf4j
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionService permissionService;

    private Map<String, Collection<ConfigAttribute>> map = null;

    @Value("${oss.tokenExpireTime}")
    private Integer tokenExpireTime;

    @Autowired
    private RedisCacheDao redisCacheDao;

    /**
     * 加载权限表中所有操作请求权限
     */
    public void loadResourceDefine(){

        map = new HashMap<>(16);
        Collection<ConfigAttribute> configAttributes;
        ConfigAttribute cfg;
        // 获取启用的权限操作请求
        List<Permission> permissions = permissionService.findByTypeAndStatusOrderBySortOrder(CommonConstant.PERMISSION_OPERATION, CommonConstant.STATUS_NORMAL);
        //缓存操作权限，用以鉴权
        setPermsToCache(permissions);

        for(Permission permission : permissions) {
            if(StrUtil.isNotBlank(permission.getTitle())&&StrUtil.isNotBlank(permission.getPath())){
                configAttributes = new ArrayList<>();
                cfg = new SecurityConfig(permission.getTitle());
                //作为MyAccessDecisionManager类的decide的第三个参数
                configAttributes.add(cfg);
                //用权限的path作为map的key，用ConfigAttribute的集合作为value
                map.put(permission.getPath(), configAttributes);
            }
        }
    }

    /**
     * 判定用户请求的url是否在权限表中
     * 如果在权限表中，则返回给decide方法，用来判定用户是否有此权限
     * 如果不在权限表中则放行
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        if(map == null){
            loadResourceDefine();
        }
        //Object中包含用户请求request
        String url = ((FilterInvocation) o).getRequestUrl();
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String resURL = iterator.next();
            if (StrUtil.isNotBlank(resURL)&&pathMatcher.match(resURL,url)) {
                return map.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 设置所有操作权限缓存
     * @param permissions
     */
    private void setPermsToCache(List<Permission> permissions){
        if(CollectionUtil.isNotEmpty(permissions)){
            List<PermissionDto> permissionDtoList = Lists.newArrayList();
            permissions.stream().forEach(item->{
                PermissionDto permissionDto = new PermissionDto();
                BeanUtils.copyProperties(item,permissionDto);
                permissionDtoList.add(permissionDto);

            });
            redisCacheDao.setEx(AuthConstant.CACHE_AUTH_PERMS,tokenExpireTime*60,permissionDtoList);
        }
    }
}
