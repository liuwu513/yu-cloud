package org.yuhan.ziyu.auth.controller.manage;

import org.yuhan.ziyu.auth.base.BaseSecurityController;
import org.yuhan.ziyu.auth.common.utils.PageUtil;
import org.yuhan.ziyu.auth.common.utils.ResultUtil;
import org.yuhan.ziyu.auth.common.vo.PageVo;
import org.yuhan.ziyu.auth.common.vo.Result;
import org.yuhan.ziyu.auth.entity.Permission;
import org.yuhan.ziyu.auth.entity.Role;
import org.yuhan.ziyu.auth.entity.UserRole;
import org.yuhan.ziyu.auth.service.RolePermissionService;
import org.yuhan.ziyu.auth.service.RoleService;
import org.yuhan.ziyu.auth.service.UserRoleService;
import org.yuhan.ziyu.auth.service.mybatis.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.yuhan.ziyu.base.common.AuthConstant;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


/**
 * @author Howell
 */
@Slf4j
@RestController
@Api(description = "角色管理接口")
@RequestMapping("/oss/role")
@Transactional
public class RoleController extends BaseSecurityController{

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/getAllList",method = RequestMethod.GET)
    @ApiOperation(value = "获取全部角色")
    public Result<Object> roleGetAll(){
        Integer dataScope = getCurrentUser().getDataScope();
        List<Role> list = roleService.getAll();
        if (AuthConstant.DataScopeEnum.ALL.getCode() != dataScope.intValue()){
            //非超管角色，移除超管角色数据查看权限
            list.removeIf(item->(item.getDataScope().intValue() == AuthConstant.DataScopeEnum.ALL.getCode()));
        }
        return new ResultUtil<Object>().setData(list);
    }

    @RequestMapping(value = "/getAllByPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页获取角色")
    public Result<Page<Role>> getRoleByPage(@ModelAttribute PageVo page){

        Integer dataScope = getCurrentUser().getDataScope();
        Page<Role> list = roleService.findAll(PageUtil.initPage(page));
        for(Role role : list.getContent()){
            List<Permission> permissions = iPermissionService.findByRoleId(role.getId());
            role.setPermissions(permissions);
        }
        List<Role> listVo = list.getContent();
        if (AuthConstant.DataScopeEnum.ALL.getCode() != dataScope.intValue()){
            //非超管角色，移除超管角色数据查看权限
            list.getContent().removeIf(item->(item.getDataScope().intValue() == AuthConstant.DataScopeEnum.ALL.getCode()));
        }
        return new ResultUtil<Page<Role>>().setData(list);
    }

    @RequestMapping(value = "/setDefault",method = RequestMethod.POST)
    @ApiOperation(value = "设置或取消默认角色")
    public Result<Object> setDefault(@RequestParam String id,
                                     @RequestParam Boolean isDefault){

        Role role = roleService.get(id);
        if(role==null){
            return new ResultUtil<Object>().setErrorMsg("角色不存在");
        }
        role.setDefaultRole(isDefault);
        roleService.update(role);
        return new ResultUtil<Object>().setSuccessMsg("设置成功");
    }

    @RequestMapping(value = "/editRolePerm/{roleId}",method = RequestMethod.POST)
    @ApiOperation(value = "编辑角色分配权限")
    public Result<Object> editRolePerm(@PathVariable String roleId,
                                       @RequestParam(required = false) String[] permIds){
        //分配权限
        rolePermissionService.assignPerms(roleId, Arrays.asList(permIds));

        //手动批量删除缓存
        Set<String> keysUser = redisTemplate.keys("user:" + "*");
        redisTemplate.delete(keysUser);
        Set<String> keysUserRole = redisTemplate.keys("userRole:" + "*");
        redisTemplate.delete(keysUserRole);
        Set<String> keysUserPerm = redisTemplate.keys("userPermission:" + "*");
        redisTemplate.delete(keysUserPerm);
        Set<String> keysUserMenu = redisTemplate.keys("permission::userMenuList:*");
        redisTemplate.delete(keysUserMenu);
        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ApiOperation(value = "保存数据")
    public Result<Role> save(@ModelAttribute Role role,@RequestParam(required = false) String[] departmentIds){
        setAddBaseEntity(role);
        Role r = roleService.save(role);
        return new ResultUtil<Role>().setData(r);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "更新数据")
    public Result<Role> edit(@ModelAttribute Role entity){
        setUpdateBaseEntity(entity);
        Role r = roleService.update(entity);
        //手动批量删除缓存
        Set<String> keysUser = redisTemplate.keys("user:" + "*");
        redisTemplate.delete(keysUser);
        Set<String> keysUserRole = redisTemplate.keys("userRole:" + "*");
        redisTemplate.delete(keysUserRole);
        return new ResultUtil<Role>().setData(r);
    }

    @RequestMapping(value = "/delAllByIds/{ids}",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过ids删除")
    public Result<Object> delByIds(@PathVariable String[] ids){

        for(String id:ids){
            List<UserRole> list = userRoleService.findByRoleId(id);
            if(list!=null&&list.size()>0){
                return new ResultUtil<Object>().setErrorMsg("删除失败，包含正被用户使用关联的角色");
            }
        }
        for(String id:ids){
            roleService.delete(id);
            //删除关联权限
            rolePermissionService.deleteByRoleId(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

}
