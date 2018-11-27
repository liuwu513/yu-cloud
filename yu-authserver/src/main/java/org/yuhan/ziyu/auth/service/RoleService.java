package org.yuhan.ziyu.auth.service;


import org.yuhan.ziyu.auth.base.OSSBaseService;
import org.yuhan.ziyu.auth.entity.Role;

import java.util.List;

/**
 * 角色接口
 * @author Howell
 */
public interface RoleService extends OSSBaseService<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);



}
