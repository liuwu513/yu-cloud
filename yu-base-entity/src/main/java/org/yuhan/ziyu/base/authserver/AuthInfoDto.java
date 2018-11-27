package org.yuhan.ziyu.base.authserver;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 鉴权信息
 * Created by liuwu on 2018/9/19 0019.
 */
@ToString
@Data
public class AuthInfoDto implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 数据作用域
     */
    private Integer dataScope;

    /**
     * 用户所属ID
     */
    private String departmentId;

    /**
     * 数据权限部门ID集合
     */
    private List<String> departmentIds;


}
