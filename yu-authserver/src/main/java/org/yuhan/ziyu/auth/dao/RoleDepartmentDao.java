package org.yuhan.ziyu.auth.dao;

import org.yuhan.ziyu.auth.base.OSSBaseDao;
import org.yuhan.ziyu.auth.entity.RoleDepartment;

import java.util.List;

/**
 * 角色权限数据处理层
 * @author Howell
 */
public interface RoleDepartmentDao extends OSSBaseDao<RoleDepartment,String> {

    /**
     * 通过departmentId获取
     * @param departmentId
     * @return
     */
    List<RoleDepartment> findByDepartmentId(String departmentId);

    /**
     * 通过角色ID获取部门信息
     * @param roleId
     * @return
     */
    List<RoleDepartment> findByRoleId(String roleId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}