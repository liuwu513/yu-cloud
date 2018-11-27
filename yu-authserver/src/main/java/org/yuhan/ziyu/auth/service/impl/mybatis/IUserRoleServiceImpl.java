package org.yuhan.ziyu.auth.service.impl.mybatis;

import org.yuhan.ziyu.auth.entity.Role;
import org.yuhan.ziyu.auth.entity.UserRole;
import org.yuhan.ziyu.auth.dao.mapper.UserRoleMapper;
import org.yuhan.ziyu.auth.service.mybatis.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Howell
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserId(String userId) {

        return userRoleMapper.findByUserId(userId);
    }
}
