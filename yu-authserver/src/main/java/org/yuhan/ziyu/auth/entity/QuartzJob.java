package org.yuhan.ziyu.auth.entity;

import org.yuhan.ziyu.auth.base.OSSBaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.yuhan.ziyu.base.common.CommonConstant;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Howell
 */
@Data
@Entity
@Table(name = "t_quartz_job")
@TableName("t_quartz_job")
public class QuartzJob extends OSSBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务类名")
    private String jobClassName;

    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;

    @ApiModelProperty(value = "参数")
    private String parameter;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "状态 0正常 -1停止")
    private Integer status = CommonConstant.STATUS_NORMAL;
}
