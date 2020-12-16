package com.quan.repository;

import com.quan.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/12/13
 */
@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
}
