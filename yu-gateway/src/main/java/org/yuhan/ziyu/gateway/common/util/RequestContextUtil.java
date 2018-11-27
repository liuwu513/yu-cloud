package org.yuhan.ziyu.gateway.common.util;

import com.netflix.zuul.context.RequestContext;
import org.yuhan.ziyu.gateway.common.GatewayConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class RequestContextUtil {

	public static String getPathParameter(String parameterName, RequestContext ctx) {
		String path = String.valueOf(ctx.getRequest().getRequestURL());

		int parameterIndex = path.indexOf(parameterName);
		if (parameterIndex < 0) {
			return null;
		}
		String[] parameterSubstring = path.substring(parameterIndex).split("/");

		if (parameterSubstring == null || parameterSubstring.length < 2) {
			return null;
		}

		return parameterSubstring[1];
	}

	/**
	 * 获取authToken
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getAuthToken(RequestContext ctx) {
		String authToken = ctx.getRequest().getHeader(GatewayConstants.AUTH_TOKEN);
		if (StringUtils.isBlank(authToken)) {
			Cookie[] cookies = ctx.getRequest().getCookies();
			authToken = CookieUtil.getCookie(GatewayConstants.AUTH_TOKEN, cookies);
		}
		return authToken;
	}

	public static String getQueryParameter(String parameterName, RequestContext ctx) {
		try {
			return ctx.getRequestQueryParams().get(parameterName).get(0);
		} catch (NullPointerException npe) {
			return null;
		}
	}

	public static void setResponseUnauthorized(RequestContext ctx, String reason) {
		ctx.setResponseStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
		ctx.setResponseBody(HttpServletResponse.SC_UNAUTHORIZED + ": " + reason);
		ctx.setSendZuulResponse(false);
	}

	public static void setResponseNotInWhiteList(RequestContext ctx, String reason) {
		ctx.setResponseStatusCode(HttpServletResponse.SC_FORBIDDEN);
		ctx.setResponseBody(HttpServletResponse.SC_FORBIDDEN + ": " + reason);
		ctx.setSendZuulResponse(false);
	}

	public static void setResponseBadRequest(RequestContext ctx, String reason) {
		ctx.setResponseStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		ctx.setResponseBody(HttpServletResponse.SC_BAD_REQUEST + ": " + reason);
		ctx.setSendZuulResponse(false);
	}

	public static void setResponseNotFound(RequestContext ctx) {
		ctx.setResponseStatusCode(HttpServletResponse.SC_NOT_FOUND);
		ctx.setSendZuulResponse(false);
	}

	public static void setResponseNotFoundCacheNotUpdate(RequestContext ctx, String jsonMessage) {
		ctx.setResponseStatusCode(HttpServletResponse.SC_NOT_FOUND);
		ctx.getResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		ctx.setResponseBody(jsonMessage);
		ctx.setSendZuulResponse(false);
	}

	public static void setResponseInternalServerError(RequestContext ctx) {
		ctx.setResponseStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ctx.setSendZuulResponse(false);
	}

	public static void writeResponse(RequestContext ctx, String response) {
		ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
		ctx.getResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		ctx.setResponseBody(response);
		ctx.setSendZuulResponse(false);
	}

	public static void setEmptyResponse(RequestContext ctx) {
		writeResponse(ctx, "{}");
	}
}
