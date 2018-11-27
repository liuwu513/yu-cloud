package org.yuhan.ziyu.auth.service;

import org.yuhan.ziyu.auth.base.OSSBaseService;
import org.yuhan.ziyu.auth.entity.Department;

import java.util.List;

/**
 * 部门接口
 *
 * @author Howell
 */
public interface DepartmentService extends OSSBaseService<Department, String> {

    /**
     * 通过父id获取 升序
     *
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(String parentId);


    /**
     * @param ownPartId 所属部门ID
     * @param parentId  父级部门ID
     * @param dataScope 数据作用域
     * @return
     */
    List<Department> findDeptByParentId(String ownPartId, String parentId, Integer dataScope);

    /**
     * 通过父id和状态获取
     *
     * @param parentId
     * @param status
     * @return
     */
    List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status);
}