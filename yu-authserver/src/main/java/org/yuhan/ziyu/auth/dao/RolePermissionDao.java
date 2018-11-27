package org.yuhan.ziyu.auth.dao;

import org.yuhan.ziyu.auth.base.OSSBaseDao;
import org.yuhan.ziyu.auth.entity.RolePermission;

import java.util.List;

/**
 * 角色权限数据处理层
 * @author Howell
 */
public interface RolePermissionDao extends OSSBaseDao<RolePermission,String> {

    /**
     * 通过permissionId获取
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 根据角色ID获取角色权限关联数据
     * @param roleId
     * @return
     */
    List<RolePermission> findByRoleId(String roleId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}