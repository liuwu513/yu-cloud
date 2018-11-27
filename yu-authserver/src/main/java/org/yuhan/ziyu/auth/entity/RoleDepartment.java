package org.yuhan.ziyu.auth.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.yuhan.ziyu.auth.base.OSSBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 角色权限关联实体
* @author liuwu
 * @date Wed Sep 19 10:18:53 CST 2018
 */
@Data
@Entity
@Table(name = "t_role_department")
@TableName("t_role_department")
@ApiModel("角色部门关联表")
public class RoleDepartment extends OSSBaseEntity {
  private static final long serialVersionUID =  8616353384846599863L;

  @ApiModelProperty("角色编号")
  @Column(name="role_id")
  private String roleId;

  @ApiModelProperty("部门ID")
  @Column(name="department_id")
  private String departmentId;
}
