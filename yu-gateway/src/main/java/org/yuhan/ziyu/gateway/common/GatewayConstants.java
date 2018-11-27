package org.yuhan.ziyu.gateway.common;

import java.nio.charset.Charset;

/**
 * <pre>
 * 描述：接口网关常量类
 * </pre>
 * 
 * @author liuwu
 * @version v2.0 2017年7月24日 下午2:09:53
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class GatewayConstants {

    private GatewayConstants() {}

    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	/** 接口网关auth-token */
    public static final String AUTH_TOKEN = "accessToken";
}
