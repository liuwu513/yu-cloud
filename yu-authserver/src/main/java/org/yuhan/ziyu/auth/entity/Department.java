package org.yuhan.ziyu.auth.entity;

import org.yuhan.ziyu.auth.base.OSSBaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.yuhan.ziyu.base.common.CommonConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * @author Howell
 */
@Data
@Entity
@Table(name = "t_department")
@TableName("t_department")
public class Department extends OSSBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门名称")
    private String title;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "是否为父节点(含子节点) 默认false")
    private Boolean isParent = false;

    @ApiModelProperty(value = "排序值")
    @Column(precision = 10, scale = 2)
    private BigDecimal sortOrder;

    @ApiModelProperty(value = "是否启用 0启用 -1禁用")
    private Integer status = CommonConstant.STATUS_NORMAL;

    @ApiModelProperty(value = "父级部门IDS,以\",\"隔开")
    private String parentIds;

    @ApiModelProperty("地区名称")
    @Column(name = "area_name")
    private String areaName;

    @ApiModelProperty("所属省")
    @Column(name = "province")
    private Integer province;

    @ApiModelProperty("所属地市")
    @Column(name = "city")
    private Integer city;

    @ApiModelProperty("所属地区")
    @Column(name = "county")
    private String county;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "父节点名称")
    private String parentTitle;

}