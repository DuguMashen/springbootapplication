package com.cc.dugumashen.controller;

import com.cc.dugumashen.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 * Date: 2019/8/30
 * Author：
 */
@RestController
@RequestMapping("/User/BaseInfo")
public class TestController {

    @Autowired
    private IUserInfoService iUserInfoService;

    @RequestMapping(value = "/allUser", method = RequestMethod.POST)
    public Map say() {
        Map response = iUserInfoService.getAllUserBaseInfo();
        return response;
    }

}
