package org.yuhan.ziyu.gateway.common.util;

import javax.servlet.http.Cookie;

/**
 * <pre>
 * 描述：cookie工具类
 * </pre>
 * 
 * @author liuwu
 * @version v2.0 2017年7月24日 上午11:41:44
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CookieUtil {

	/**
	 * 根据key获取请求cookies中的该key对应的值
	 * 
	 * @param key
	 * @param cookies
	 * @return
	 */
	public static String getCookie(String key, Cookie[] cookies) {
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
