package org.yuhan.ziyu.base.common;

/**
 * 鉴权相关信息
 * Created by liuwu on 2018/9/18 0018.
 */
public class AuthConstant {

    /**
     * 所有操作权限缓存key
     */
    public static final String CACHE_AUTH_PERMS = "CACHE_AUTH_PERMS";

    public enum DataScopeEnum {
        ALL(0, "所有数据"),

        OWN_COMPANY_DOWN(1, "所在公司及以下数据"),

        OWN_COMPANY(2, "所在公司数据"),

        OWN_DEPARTMENT_DOWN(3, "所在部门及以下数据"),

        OWN_DEPARTMENT(4, "所在部门数据"),

        SELF(8, "本人数据");

        private int code;

        private String msg;

        DataScopeEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}