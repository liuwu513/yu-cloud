package org.yuhan.ziyu.auth.base;

import org.yuhan.ziyu.auth.entity.User;
import org.yuhan.ziyu.auth.exception.OSSException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.yuhan.ziyu.base.authserver.AuthInfoDto;

import java.util.Date;

/**
 * 获取授权信息
 * Created by liuwu on 2018/9/14 0014.
 */
public abstract class BaseSecurityController {

    /**
     * 获取用户信息
     *
     * @return
     */
    protected AuthInfoDto getCurrentUser() {
        AuthInfoDto authInfoDto = (AuthInfoDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (authInfoDto == null) {
            throw new OSSException("用户信息获取失败，请重新登录");
        }
        return authInfoDto;
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    protected String getCurrentUserId() {
        return getCurrentUser().getUserId();
    }

    /**
     * 获取当前用户所属部门ID
     * @return
     */
    protected String getDepartmentId(){
        return getCurrentUser().getDepartmentId();
    }

    /**
     * 设置用户鉴权信息
     * @param user
     */
    protected void setAuthInfo(User user){
        AuthInfoDto authInfoDto = getCurrentUser();
        user.setDataScope(authInfoDto.getDataScope());
        user.setDepartmentIds(authInfoDto.getDepartmentIds());
    }

    /**
     * 设置保存信息
     *
     * @param baseEntity
     */
    protected void setAddBaseEntity(OSSBaseEntity baseEntity) {
        baseEntity.setCreateBy(getCurrentUserId());
        baseEntity.setCreateTime(new Date());
    }

    /**
     * 设置更新信息
     *
     * @param baseEntity
     */
    protected void setUpdateBaseEntity(OSSBaseEntity baseEntity) {
        baseEntity.setUpdateBy(getCurrentUserId());
        baseEntity.setUpdateTime(new Date());
    }

    /**
     * 设置公共信息
     * @param baseEntity
     */
    protected void setBaseEntity(OSSBaseEntity baseEntity) {
        String userId = getCurrentUserId();
        baseEntity.setCreateBy(userId);
        baseEntity.setCreateTime(new Date());
        baseEntity.setUpdateBy(userId);
        baseEntity.setUpdateTime(new Date());
    }
}
