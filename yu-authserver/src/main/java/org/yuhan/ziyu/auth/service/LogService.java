package org.yuhan.ziyu.auth.service;


import org.yuhan.ziyu.auth.base.OSSBaseService;
import org.yuhan.ziyu.auth.common.vo.SearchVo;
import org.yuhan.ziyu.auth.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 日志接口
 * @author Howell
 */
public interface LogService extends OSSBaseService<Log,String> {

    /**
     * 日志搜索
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Log> searchLog(String key, SearchVo searchVo, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
