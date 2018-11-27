package org.yuhan.ziyu.auth.service;

import org.yuhan.ziyu.auth.base.OSSBaseService;
import org.yuhan.ziyu.auth.entity.QuartzJob;

import java.util.List;

/**
 * 定时任务接口
 * @author Howell
 */
public interface QuartzJobService extends OSSBaseService<QuartzJob,String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}