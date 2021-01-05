package com.quan.repository;

import com.quan.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

    Dish findFirstByShopIdAndDishNameAndStatus(Integer shopId, String dishName, Byte status);

    Page<Dish> findAllByShopIdInOrderByCreateTimeDesc(Set<Integer> shopIds, Pageable pageable);

    List<Dish> findAllByShopIdAndCategory(Integer shopId, Byte category);
}
