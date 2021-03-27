package com.lbh.service.impl;

import com.lbh.dao.UserDao;
import com.lbh.entity.User;
import com.lbh.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author lbh
 * @Date 2021/3/27
 * @Description:
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void save(User user) {

        if(userDao.selectByUsername(user.getUsername())!=null) throw new RuntimeException("当前用户名已存在");
        user.setStatus("已激活");
        user.setCreateTime(new Date());

        userDao.save(user);
    }

    @Override
    public User login(User user) {

        User userDB = userDao.selectByUsername(user.getUsername());
        if(userDB==null) throw new RuntimeException("用户名不存在");
        if(!user.getPassword().equals(userDB.getPassword())) throw new RuntimeException("密码错误");

        return userDB;
    }

}
