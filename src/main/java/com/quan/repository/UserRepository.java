package com.quan.repository;

import com.quan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: xiexinquan520@163.com
 * User: XieXinQuan
 * DATE:2020/11/14
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 用户名查找
     * @param name
     * @return
     */
    User findFirstByUserName(String name);

    User findFirstByIdAndAndType(Integer id, Byte type);

    List<User> findAllByNickNameLikeAndStatus(String nickName, Byte status);
}
