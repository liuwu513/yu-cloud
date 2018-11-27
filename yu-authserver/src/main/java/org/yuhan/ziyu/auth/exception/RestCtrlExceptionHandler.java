package org.yuhan.ziyu.auth.exception;

import org.yuhan.ziyu.auth.common.utils.ResultUtil;
import org.yuhan.ziyu.auth.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Howell
 */
@Slf4j
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    @ExceptionHandler(OSSException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleXCloudException(OSSException e) {

        String errorMsg="oss exception";
        if (e!=null){
            errorMsg=e.getMsg();
            log.error(e.toString());
        }
        return new ResultUtil<>().setErrorMsg(500, errorMsg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception e) {

        String errorMsg="Exception";
        if (e!=null){
            errorMsg=e.getMessage();
            log.error(e.toString());
        }
        return new ResultUtil<>().setErrorMsg(500, errorMsg);
    }
}
