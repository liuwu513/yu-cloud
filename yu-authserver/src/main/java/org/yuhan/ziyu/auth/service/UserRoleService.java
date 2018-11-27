package org.yuhan.ziyu.auth.service;


import org.yuhan.ziyu.auth.base.OSSBaseService;
import org.yuhan.ziyu.auth.entity.UserRole;

import java.util.List;

/**
 * 用户角色接口
 * @author Howell
 */
public interface UserRoleService extends OSSBaseService<UserRole,String> {

    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(String roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(String userId);
}
