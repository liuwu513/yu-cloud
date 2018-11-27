package org.yuhan.ziyu.auth.dao;

import org.yuhan.ziyu.auth.base.OSSBaseDao;
import org.yuhan.ziyu.auth.entity.UserRole;

import java.util.List;

/**
 * 用户角色数据处理层
 * @author Howell
 */
public interface UserRoleDao extends OSSBaseDao<UserRole,String> {

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
