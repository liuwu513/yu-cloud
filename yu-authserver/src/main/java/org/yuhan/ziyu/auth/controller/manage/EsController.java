package org.yuhan.ziyu.auth.controller.manage;

import org.yuhan.ziyu.auth.base.BaseSecurityController;
import org.yuhan.ziyu.auth.common.utils.ResultUtil;
import org.yuhan.ziyu.auth.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * @author Howell
 */
@Slf4j
@RestController
@Api(description = "Elasticsearch信息接口")
@RequestMapping("/oss/es")
@Transactional
public class EsController extends BaseSecurityController {

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "获取es状态")
    public Result<Object> getAllByPage(){

        return new ResultUtil<Object>().setData("ok");
    }
}
