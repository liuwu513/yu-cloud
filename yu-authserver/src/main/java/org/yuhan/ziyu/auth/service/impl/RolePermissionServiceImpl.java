package org.yuhan.ziyu.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import org.yuhan.ziyu.auth.dao.PermissionDao;
import org.yuhan.ziyu.auth.dao.RolePermissionDao;
import org.yuhan.ziyu.auth.entity.Permission;
import org.yuhan.ziyu.auth.entity.RolePermission;
import org.yuhan.ziyu.auth.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色权限接口实现
 *
 * @author Howell
 */
@Slf4j
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public RolePermissionDao getRepository() {
        return rolePermissionDao;
    }

    @Override
    public List<RolePermission> findByPermissionId(String permissionId) {
        return rolePermissionDao.findByPermissionId(permissionId);
    }

    @Override
    public void deleteByRoleId(String roleId) {
        rolePermissionDao.deleteByRoleId(roleId);
    }

    /**
     * 分配权限
     *
     * @param roleId  角色ID
     * @param permIds 权限ID集合
     * @return
     */
    @Override
    public Boolean assignPerms(String roleId, List<String> permIds) {
        List<RolePermission> currentPerms = rolePermissionDao.findByRoleId(roleId);
        //获取权限数据
        List<Permission> permissionList = permissionDao.findAllById(permIds);

        List<String> currentPermIds = currentPerms.stream().map(item->item.getPermissionId()).collect(Collectors.toList());
        List<String> newPermIds = permissionList.stream().map(item->item.getId()).collect(Collectors.toList());

        List<String> opNewPermIds = newPermIds.stream().collect(Collectors.toList());

        //获取新增数据
        opNewPermIds.removeAll(currentPermIds);

        //获取新分配权限中，之前已存在权限
        newPermIds.removeAll(opNewPermIds);

        //获取需删除权限数据
        currentPermIds.removeAll(newPermIds);

        //新增逻辑
        if (CollectionUtil.isNotEmpty(opNewPermIds)){
            List<RolePermission> newPermList = Lists.newArrayList();
            for (String newPermId : opNewPermIds) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(newPermId);
                //TODO 后续补充，获取用户信息
                rolePermission.setCreateBy(StringUtils.EMPTY);
                rolePermission.setCreateTime(new Date());
                newPermList.add(rolePermission);
            }
            rolePermissionDao.saveAll(newPermList);
        }

        //更新字段
        if (CollectionUtil.isNotEmpty(newPermIds)){
            //修改逻辑
            List<RolePermission> needUpdateList = currentPerms.stream().filter(item->newPermIds.contains(item.getPermissionId())).
                    collect(Collectors.toList());
            for (RolePermission rolePermission : needUpdateList) {
                rolePermission.setUpdateBy(StringUtils.EMPTY);
                rolePermission.setUpdateTime(new Date());
            }
            rolePermissionDao.saveAll(needUpdateList);
        }

        //删除逻辑
        if (CollectionUtil.isNotEmpty(currentPermIds)){
            List<RolePermission> needDelList = currentPerms.stream().filter(item->currentPermIds.contains(item.getPermissionId())).
                    collect(Collectors.toList());
            rolePermissionDao.deleteInBatch(needDelList);
        }
        return Boolean.TRUE;
    }
}