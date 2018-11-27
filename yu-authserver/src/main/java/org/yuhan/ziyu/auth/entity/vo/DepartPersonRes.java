package org.yuhan.ziyu.auth.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 部门人员结构
 * Created by liuwu on 2018/9/26 0026.
 */
@ToString
@Data
public class DepartPersonRes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门/用户 id")
    private String id;

    @ApiModelProperty(value = "部门/用户 名称")
    private String text;

    @ApiModelProperty(value = "类型 1:部门,2:用户")
    private Integer dpType;

    @ApiModelProperty(value = "部门ID(类型为用户时才有值)")
    private String departmentId;
}
