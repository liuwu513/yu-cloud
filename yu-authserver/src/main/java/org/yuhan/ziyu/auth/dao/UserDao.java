package org.yuhan.ziyu.auth.dao;

import org.yuhan.ziyu.auth.base.OSSBaseDao;
import org.yuhan.ziyu.auth.entity.User;

import java.util.List;

/**
 * 用户数据处理层
 * @author Howell
 */
public interface UserDao extends OSSBaseDao<User,String> {

    /**
     * 通过用户名和状态获取用户
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameAndStatus(String username, Integer status);

    /**
     * 通过状态和类型获取用户
     * @param status
     * @param type
     * @return
     */
    List<User> findByStatusAndType(Integer status, Integer type);

    /**
     * 通过部门id获取
     * @param departmentId
     * @return
     */
    List<User> findByDepartmentId(String departmentId);
}
