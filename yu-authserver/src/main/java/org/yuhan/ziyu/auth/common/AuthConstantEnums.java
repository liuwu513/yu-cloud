package org.yuhan.ziyu.auth.common;

import lombok.Getter;

/**
 * 枚举类
 * Created by liuwu on 2018/9/26 0026.
 */
public class AuthConstantEnums {

    /**
     * 部门人员结构类型枚举
     */
    public enum DpTypeEnum {
        D(1, "部门"), P(2, "人员");

        DpTypeEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Getter
        private int code;

        @Getter
        private String desc;
    }
}
