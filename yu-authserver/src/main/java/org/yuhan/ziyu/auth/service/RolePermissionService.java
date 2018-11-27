package org.yuhan.ziyu.auth.service;

import org.yuhan.ziyu.auth.base.OSSBaseService;
import org.yuhan.ziyu.auth.entity.RolePermission;

import java.util.List;

/**
 * 角色权限接口
 *
 * @author Howell
 */
public interface RolePermissionService extends OSSBaseService<RolePermission, String> {


    /**
     * 分配权限
     *
     * @param roleId  角色ID
     * @param permIds 权限ID集合
     * @return
     */
    Boolean assignPerms(String roleId, List<String> permIds);

    /**
     * 通过permissionId获取
     *
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 通过roleId删除
     *
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}