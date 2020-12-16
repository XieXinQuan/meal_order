package com.quan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/12
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}
