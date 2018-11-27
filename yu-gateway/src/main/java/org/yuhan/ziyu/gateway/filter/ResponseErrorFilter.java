package org.yuhan.ziyu.gateway.filter;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.yuhan.ziyu.gateway.common.FilterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.ListIterator;

/**
 * <pre>
 * 描述：响应错误过滤器
 * </pre>
 * 
 * @author liuwu
 * @version v2.0 2017年7月24日 上午11:28:55
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class ResponseErrorFilter extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(ResponseErrorFilter.class);

	@Value("${logZuulExceptions}")
	private boolean logZuulExceptions;

	@Override
	public String filterType() {
		return FilterConstants.POST_FILTER;
	}

	@Override
	public int filterOrder() {
		return 2;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String responseStatusCode = String.valueOf(ctx.getResponseStatusCode());
		return responseStatusCode.startsWith("5") || responseStatusCode.startsWith("4");
	}

	@Override
	public Object run() {
		log.debug("Running filter " + getClass().getSimpleName());
		filterErrorMessage();
		return null;
	}

	protected void filterErrorMessage() {
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.removeRouteHost();
		List<Pair<String, String>> zuulResponseHeaders = ctx.getZuulResponseHeaders();
		ListIterator<Pair<String, String>> iterator = zuulResponseHeaders.listIterator();
		while (iterator.hasNext()) {
			Pair<String, String> responseHeader = iterator.next();
			if (responseHeader.first().equals(FilterConstants.ERROR_MESSAGE)) {
				if (logZuulExceptions) {
					log.warn(getClass().getSimpleName(), "Error message:" + responseHeader.second());
				}
				responseHeader.setSecond("See gateway log files for full error message.");
			}
		}
	}
}
