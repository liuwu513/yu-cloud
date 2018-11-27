package org.yuhan.ziyu.auth.controller.manage;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import org.yuhan.ziyu.auth.base.BaseSecurityController;
import org.yuhan.ziyu.auth.common.AuthConstantEnums;
import org.yuhan.ziyu.auth.common.utils.ResultUtil;
import org.yuhan.ziyu.auth.common.vo.Result;
import org.yuhan.ziyu.auth.entity.User;
import org.yuhan.ziyu.auth.entity.vo.DepartPersonRes;
import org.yuhan.ziyu.auth.service.UserService;
import org.yuhan.ziyu.auth.entity.Department;
import org.yuhan.ziyu.auth.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.yuhan.ziyu.base.authserver.AuthInfoDto;
import org.yuhan.ziyu.base.common.CommonConstant;

import java.util.List;
import java.util.Set;


/**
 * @author Howell
 */
@Slf4j
@RestController
@Api(description = "部门管理接口")
@RequestMapping("/oss/department")
@CacheConfig(cacheNames = "department")
@Transactional
public class DepartmentController extends BaseSecurityController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/getByParentId/{parentId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
//    @Cacheable(key = "#parentId")
    public Result<List<Department>> getByParentId(@PathVariable String parentId) {

        //List<Department> list = departmentService.findByParentIdOrderBySortOrder(parentId);
        AuthInfoDto authInfo = getCurrentUser();
        //数据权限控制
        List<Department> list = departmentService.findDeptByParentId(authInfo.getDepartmentId(), parentId,
                authInfo.getDataScope());
        // lambda表达式
        list.forEach(item -> {
            if (!CommonConstant.PARENT_ID.equals(item.getParentId())) {
                Department parent = departmentService.get(item.getParentId());
                item.setParentTitle(parent.getTitle());
            } else {
                item.setParentTitle("一级部门");
            }
        });
        return new ResultUtil<List<Department>>().setData(list);
    }


    @RequestMapping(value = "/getDepartPersonByPid/{parentId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据部门父ID获取下属部门以及人员")
//    @Cacheable(key = "#parentId")
    public Result<List<DepartPersonRes>> getDepartPersonByPid(@PathVariable String parentId) {

        AuthInfoDto authInfo = getCurrentUser();
        //数据权限控制
        List<Department> list = departmentService.findDeptByParentId(authInfo.getDepartmentId(), parentId,
                authInfo.getDataScope());
        List<DepartPersonRes> dpResList = Lists.newArrayList();
        // lambda表达式
        list.forEach(item -> {
            DepartPersonRes dRes = new DepartPersonRes();
            dRes.setId(item.getId());
            dRes.setText(item.getTitle());
            dRes.setDpType(AuthConstantEnums.DpTypeEnum.D.getCode());
            dpResList.add(dRes);
            if (!CommonConstant.PARENT_ID.equals(item.getParentId())) {
                Department parent = departmentService.get(item.getParentId());
                item.setParentTitle(parent.getTitle());
            } else {
                item.setParentTitle("一级部门");
            }
            List<User> userList = userService.findByDepartmentId(item.getId());
            if (CollectionUtil.isNotEmpty(userList)) {
                userList.forEach(user -> {
                    DepartPersonRes pRes = new DepartPersonRes();
                    pRes.setId(user.getId());
                    pRes.setText(user.getNickName());
                    pRes.setDpType(AuthConstantEnums.DpTypeEnum.P.getCode());
                    pRes.setDepartmentId(user.getDepartmentId());
                    dpResList.add(pRes);
                });
            }
        });
        return new ResultUtil<List<DepartPersonRes>>().setData(dpResList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @CacheEvict(key = "#department.parentId")
    public Result<Department> add(@ModelAttribute Department department) {
        setBaseEntity(department);
        Department d = departmentService.save(department);
        // 如果不是添加的一级 判断设置上级为父节点标识
        if (!CommonConstant.PARENT_ID.equals(department.getParentId())) {
            Department parent = departmentService.get(department.getParentId());
            department.setParentIds(String.format(parent.getParentIds() + ",%s", parent.getId()));

            if (parent.getIsParent() == null || !parent.getIsParent()) {
                parent.setIsParent(true);
                departmentService.update(parent);
                // 更新上级节点的缓存
                redisTemplate.delete("department::" + parent.getParentId());
            }
        } else {
            //更新父级parentIds,用于数据权限鉴权
            department.setParentIds(StringUtils.EMPTY);
        }
        departmentService.update(department);
        return new ResultUtil<Department>().setData(d);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ApiOperation(value = "编辑")
    public Result<Department> edit(@ModelAttribute Department department) {
        if (!CommonConstant.PARENT_ID.equals(department.getParentId())) {
            Department parent = departmentService.get(department.getParentId());
            department.setParentIds(String.format(parent.getParentIds() + ",%s", parent.getId()));
        } else {
            //更新父级parentIds,用于数据权限鉴权
            department.setParentIds(StringUtils.EMPTY);
        }
        setUpdateBaseEntity(department);
        Department d = departmentService.update(department);
        // 手动删除所有部门缓存
        Set<String> keys = redisTemplate.keys("department:" + "*");
        redisTemplate.delete(keys);
        // 删除所有用户缓存
        Set<String> keysUser = redisTemplate.keys("user:" + "*");
        redisTemplate.delete(keysUser);
        return new ResultUtil<Department>().setData(d);
    }

    @RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@PathVariable String[] ids) {

        for (String id : ids) {
            List<User> list = userService.findByDepartmentId(id);
            if (list != null && list.size() > 0) {
                return new ResultUtil<Object>().setErrorMsg("删除失败，包含正被用户使用关联的部门");
            }
        }
        for (String id : ids) {
            departmentService.delete(id);
        }
        // 手动删除所有部门缓存
        Set<String> keys = redisTemplate.keys("department:" + "*");
        redisTemplate.delete(keys);
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

}
