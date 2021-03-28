package com.lbh.dao;

import com.lbh.entity.Emp;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/28
 * @Description:
 */
public interface EmpDao {
    List<Emp> findAll();

    void save(Emp emp);
}
