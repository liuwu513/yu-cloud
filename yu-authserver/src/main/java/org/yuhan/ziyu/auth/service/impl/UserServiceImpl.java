package org.yuhan.ziyu.auth.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import org.yuhan.ziyu.auth.common.vo.SearchVo;
import org.yuhan.ziyu.auth.dao.DepartmentDao;
import org.yuhan.ziyu.auth.dao.UserDao;
import org.yuhan.ziyu.auth.dao.mapper.PermissionMapper;
import org.yuhan.ziyu.auth.dao.mapper.UserRoleMapper;
import org.yuhan.ziyu.auth.entity.Permission;
import org.yuhan.ziyu.auth.entity.Role;
import org.yuhan.ziyu.auth.entity.User;
import org.yuhan.ziyu.auth.service.DepartmentService;
import org.yuhan.ziyu.auth.service.UserService;
import org.yuhan.ziyu.auth.entity.Department;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yuhan.ziyu.base.common.AuthConstant;
import org.yuhan.ziyu.base.common.CommonConstant;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口实现
 *
 * @author Howell
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public UserDao getRepository() {
        return userDao;
    }

    @Override
    public User findByUsername(String username) {

        List<User> list = userDao.findByUsernameAndStatus(username, CommonConstant.USER_STATUS_NORMAL);
        if (list != null && list.size() > 0) {
            User user = list.get(0);
            // 关联部门
            if (StrUtil.isNotBlank(user.getDepartmentId())) {
                Department department = departmentDao.getOne(user.getDepartmentId());
                user.setDepartmentTitle(department.getTitle());
            }
            // 关联角色
            List<Role> roleList = userRoleMapper.findByUserId(user.getId());
            //关联数据权限信息
            setAuthInfo(user, roleList);

            user.setRoles(roleList);
            // 关联权限菜单
            List<Permission> permissionList = permissionMapper.findByUserId(user.getId());
            user.setPermissions(permissionList);
            return user;
        }
        return null;
    }

    /**
     * 设置权限信息
     * @param user
     * @param roleList
     * @return
     */
    private void setAuthInfo(User user, List<Role> roleList) {
        if (CollectionUtil.isNotEmpty(roleList)) {
            //作用域排序
            roleList.sort(new Comparator<Role>() {
                @Override
                public int compare(Role o1, Role o2) {
                    return o1.getDataScope().compareTo(o2.getDataScope());
                }
            });

            //获取最大权限数据作用域
            Role role = roleList.get(0);

            //如若有一作用域大于 自有数据，则获取数据权限部门IDS
            boolean hasDataPerm = roleList.stream().anyMatch(item -> item.getDataScope() <
                    AuthConstant.DataScopeEnum.SELF.getCode());

            if (hasDataPerm) {
                List<Department> departments = departmentService.findDeptByParentId(user.getDepartmentId(),
                        StringUtils.EMPTY, role.getDataScope());
                List<String> departmentIds = departments.stream().map(item -> item.getId()).collect(Collectors.toList());
                user.setDepartmentIds(departmentIds);
            }
            user.setDataScope(role.getDataScope());
        }
    }

    @Override
    public Page<User> findByCondition(User user, SearchVo searchVo, Pageable pageable) {

        return userDao.findAll(new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Path<String> userIdField = root.get("id");
                Path<String> usernameField = root.get("username");
                Path<String> jobNumField = root.get("jobNum");
                Path<String> mobileField = root.get("mobile");
                Path<String> emailField = root.get("email");
                Path<String> departmentIdField = root.get("departmentId");
                Path<Integer> sexField = root.get("sex");
                Path<Integer> typeField = root.get("type");
                Path<Integer> statusField = root.get("status");
                Path<Date> createTimeField = root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //查询本人或者查询本人所属部门以下数据
                if (user.getDataScope().equals(AuthConstant.DataScopeEnum.SELF)){
                    list.add(cb.equal(userIdField,user.getId()));
                }else {
                    CriteriaBuilder.In<Object> in = cb.in(departmentIdField);
                    for (String departmentId : user.getDepartmentIds()) {
                        in.value(departmentId);
                    }
                    list.add(in);
                }

                //模糊搜素
                if (StrUtil.isNotBlank(user.getUsername())) {
                    list.add(cb.like(usernameField, '%' + user.getUsername() + '%'));
                }
                if (StrUtil.isNotBlank(user.getJobNum())) {
                    list.add(cb.like(jobNumField, '%' + user.getJobNum() + '%'));
                }
                if (StrUtil.isNotBlank(user.getMobile())) {
                    list.add(cb.like(mobileField, '%' + user.getMobile() + '%'));
                }
                if (StrUtil.isNotBlank(user.getEmail())) {
                    list.add(cb.like(emailField, '%' + user.getEmail() + '%'));
                }

                //部门
                if (StrUtil.isNotBlank(user.getDepartmentId())) {
                    list.add(cb.equal(departmentIdField, user.getDepartmentId()));
                }

                //性别
                if (user.getSex() != null) {
                    list.add(cb.equal(sexField, user.getSex()));
                }
                //类型
                if (user.getType() != null) {
                    list.add(cb.equal(typeField, user.getType()));
                }
                //状态
                if (user.getStatus() != null) {
                    list.add(cb.equal(statusField, user.getStatus()));
                }
                //创建时间
                if (StrUtil.isNotBlank(searchVo.getStartDate()) && StrUtil.isNotBlank(searchVo.getEndDate())) {
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

    @Override
    public List<User> findByDepartmentId(String departmentId) {

        return userDao.findByDepartmentId(departmentId);
    }
}
