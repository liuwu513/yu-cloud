package org.yuhan.ziyu.auth.dao;

import org.yuhan.ziyu.auth.base.OSSBaseDao;
import org.yuhan.ziyu.auth.entity.QuartzJob;

import java.util.List;

/**
 * 定时任务数据处理层
 * @author Howell
 */
public interface QuartzJobDao extends OSSBaseDao<QuartzJob,String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}