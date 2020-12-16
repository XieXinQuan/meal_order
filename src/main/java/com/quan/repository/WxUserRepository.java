package com.quan.repository;

import com.quan.model.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/5
 */
@Repository
public interface WxUserRepository extends JpaRepository<WxUser, Integer> {
    /**
     * 获取微信登录用户
     * @param appId
     * @return
     */
    WxUser findFirstByWxAppId(String appId);


}
