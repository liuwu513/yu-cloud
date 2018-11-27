package org.yuhan.ziyu.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.yuhan.ziyu.gateway.common.FilterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * <pre>
 * 描述：字符集过滤器
 * </pre>
 * 
 * @author liuwu
 * @version v2.0 2017年7月24日 上午11:26:38
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CharacterEncodingPostFilter extends ZuulFilter {

	private static final Logger log = LoggerFactory.getLogger(CharacterEncodingPostFilter.class);

	@Override
	public String filterType() {
		return FilterConstants.POST_FILTER;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.debug("Running filter " + getClass().getSimpleName());

		RequestContext ctx = RequestContext.getCurrentContext();
		if (ctx.getResponse() != null) {
			String requestEncoding = ctx.getRequest().getCharacterEncoding();
			if (requestEncoding != null && isCharsetSupported(requestEncoding)) {
				ctx.getResponse().setCharacterEncoding(requestEncoding);
			} else {
				ctx.getResponse().setCharacterEncoding(FilterConstants.UTF_8_ENCODING);
			}
		}
		return null;
	}

	private Boolean isCharsetSupported(String name) {
		return Charset.availableCharsets().keySet().contains(name);
	}
}
