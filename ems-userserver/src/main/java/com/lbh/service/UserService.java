package com.lbh.service;

import com.lbh.entity.User;

/**
 * @Author lbh
 * @Date 2021/3/27
 * @Description:
 */
public interface UserService {
    void save(User user);

    User login(User user);
}
