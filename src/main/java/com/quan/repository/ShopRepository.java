package com.quan.repository;

import com.quan.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

    Shop findFirstByNameAndStatus(String shopName, Byte status);

    List<Shop> findAllByShopManagerEqualsOrCreateUserOrderByIdAsc(Integer shopManager, Integer createUser);

    List<Shop> findAllByNameLikeAndStatus(String name, Byte status);
}
