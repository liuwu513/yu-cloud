package org.yuhan.ziyu.auth.service.impl;

import org.yuhan.ziyu.auth.dao.DepartmentDao;
import org.yuhan.ziyu.auth.dao.mapper.DepartmentMapper;
import org.yuhan.ziyu.auth.entity.Department;
import org.yuhan.ziyu.auth.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门接口实现
 *
 * @author Howell
 */
@Slf4j
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public DepartmentDao getRepository() {
        return departmentDao;
    }

    @Override
    public List<Department> findByParentIdOrderBySortOrder(String parentId) {

        return departmentDao.findByParentIdOrderBySortOrder(parentId);
    }

    /**
     * 跟进父级部门ID获取下级部门(增加权限控制)
     *
     * @param ownPartId 所属部门ID
     * @param parentId  父级部门ID
     * @param dataScope
     * @return
     */
    @Override
    public List<Department> findDeptByParentId(String ownPartId, String parentId, Integer dataScope) {
        return departmentMapper.findDeptByParentId(ownPartId, parentId,dataScope);
    }

    @Override
    public List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status) {

        return departmentDao.findByParentIdAndStatusOrderBySortOrder(parentId, status);
    }
}