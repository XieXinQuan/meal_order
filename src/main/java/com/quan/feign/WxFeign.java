package com.quan.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/5
 */
@FeignClient(name = "wx", url = "https://api.weixin.qq.com/sns/jscode2session")
public interface WxFeign {
    @GetMapping
    JSONObject getWxUserInfo(@RequestParam("appid") String appid, @RequestParam("secret") String secret,
                             @RequestParam("js_code") String js_code, @RequestParam("grant_type") String grant_type);
}
