package org.yuhan.ziyu.auth.service.impl;

import org.yuhan.ziyu.auth.base.OSSBaseDao;
import org.yuhan.ziyu.auth.dao.RoleDepartmentDao;
import org.yuhan.ziyu.auth.entity.RoleDepartment;
import org.yuhan.ziyu.auth.service.RoleDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuwu on 2018/9/19 0019.
 */
@Service
@Transactional(readOnly = true)
public class RoleDepartmentServiceImpl implements RoleDepartmentService {

    @Autowired
    private RoleDepartmentDao roleDepartmentDao;

    @Override
    public OSSBaseDao<RoleDepartment, String> getRepository() {
        return roleDepartmentDao;
    }

    /**
     * 通过departmentId获取
     *
     * @param departmentId
     * @return
     */
    @Override
    public List<RoleDepartment> findByDepartmentId(String departmentId) {
        return roleDepartmentDao.findByDepartmentId(departmentId);
    }

    /**
     * 通过角色ID获取部门信息
     *
     * @param roleId
     * @return
     */
    @Override
    public List<RoleDepartment> findByRoleId(String roleId) {
        return roleDepartmentDao.findByRoleId(roleId);
    }

    /**
     * 通过roleId删除
     *
     * @param roleId
     */
    @Override
    public void deleteByRoleId(String roleId) {
        roleDepartmentDao.deleteByRoleId(roleId);
    }
}
