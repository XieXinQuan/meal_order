package com.quan.repository;

import com.quan.model.ShopEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Repository
public interface ShopEmployeeRepository extends JpaRepository<ShopEmployee, Integer> {

    List<ShopEmployee> findAllByShopId(Integer shopId);

    List<ShopEmployee> findAllByUserIdOrCreateUser(Integer userId, Integer createUser);
}
