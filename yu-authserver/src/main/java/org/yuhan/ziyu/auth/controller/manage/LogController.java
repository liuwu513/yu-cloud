package org.yuhan.ziyu.auth.controller.manage;

import org.yuhan.ziyu.auth.common.utils.PageUtil;
import org.yuhan.ziyu.auth.common.utils.ResultUtil;
import org.yuhan.ziyu.auth.common.vo.PageVo;
import org.yuhan.ziyu.auth.common.vo.Result;
import org.yuhan.ziyu.auth.common.vo.SearchVo;
import org.yuhan.ziyu.auth.service.LogService;
import org.yuhan.ziyu.auth.entity.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * @author Howell
 */
@Slf4j
@RestController
@Api(description = "日志管理接口")
@RequestMapping("/oss/log")
@Transactional
public class LogController{

    @Value("${oss.logRecord.es}")
    private Boolean esRecord;



    @Autowired
    private LogService logService;

    @RequestMapping(value = "/getAllByPage",method = RequestMethod.GET)
    @ApiOperation(value = "分页获取全部")
    public Result<Object> getAllByPage(@ModelAttribute PageVo pageVo){


            Page<Log> log = logService.findAll(PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(log);

    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiOperation(value = "分页搜索")
    public Result<Object> search(@RequestParam String key,
                                 @ModelAttribute SearchVo searchVo,
                                 @ModelAttribute PageVo pageVo){
            Page<Log> log = logService.searchLog(key, searchVo, PageUtil.initPage(pageVo));
            return new ResultUtil<Object>().setData(log);
    }

    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除")
    public Result<Object> delByIds(@PathVariable String[] ids){

        for(String id : ids){

                logService.delete(id);

        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }

    @RequestMapping(value = "/delAll",method = RequestMethod.DELETE)
    @ApiOperation(value = "全部删除")
    public Result<Object> delAll(){


        logService.deleteAll();

        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }
}
