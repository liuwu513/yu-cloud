package org.yuhan.ziyu.gateway.common.result;

/**
 * <pre>
 * 描述：返回信息
 * </pre>
 * 
 * @author liuwu
 * @version v2.0 2017年8月4日 上午10:20:43
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class ReturnMessage {

	/** 鉴权码错误信息 */
	public static final String UNAUTHORIZED_TOKEN_MSG = "缺少鉴权码auth_token";

	/** 接口尚未授权 */
	public static final String SC_FORBIDDEN_MSG = "接口尚未授权";

	/** token过期 */
	public static final String SC_EXPRIED_MSG = "登录已失效，请重新登录";

}
