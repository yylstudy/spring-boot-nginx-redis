package com.yyl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2020-11-10
 */
@RestController
public class MyController {
    @RequestMapping("test1")
    public String test1(){
        System.out.println("----------test1---------");
        return "success";
    }
    @RequestMapping("getUser")
    public Object getUser(){
        MyUser myUser = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("----------myUser---------");
        return myUser;
    }
}
