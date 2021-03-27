package com.lbh.dao;

import com.lbh.entity.User;

/**
 * @Author lbh
 * @Date 2021/3/27
 * @Description:
 */

public interface UserDao {
    void save(User user);

    User selectByUsername(String username);

}
