package org.yuhan.ziyu.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuwu on 2018/9/11 0011.
 */
@RestController
@RequestMapping(value = "api")
public class RestTestController {

    @RequestMapping(value = "test")
    public String getMessage(){
        return "hello";
    }
}
