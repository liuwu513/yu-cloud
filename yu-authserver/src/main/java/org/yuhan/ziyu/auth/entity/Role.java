package org.yuhan.ziyu.auth.entity;

import org.yuhan.ziyu.auth.base.OSSBaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author Howell
 */
@Data
@Entity
@Table(name = "t_role")
@TableName("t_role")
public class Role extends OSSBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名 以ROLE_开头")
    private String name;

    @ApiModelProperty(value = "数据范围（0：所有数据；1：所在公司及以下数据；2：所在公司数据；3：所在部门及以下数据；4：所在部门   数据；8：仅本人数据；9：按明细设置）")
    private Integer dataScope;

    @ApiModelProperty(value = "是否为注册默认角色")
    private Boolean defaultRole;

    @ApiModelProperty(value = "备注")
    private String description;

    @Transient
    @TableField(exist = false)
    @ApiModelProperty(value = "拥有权限")
    private List<Permission> permissions;
}
