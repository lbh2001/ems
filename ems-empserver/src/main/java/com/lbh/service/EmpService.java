package com.lbh.service;

import com.lbh.entity.Emp;

import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/28
 * @Description:
 */
public interface EmpService {
    List<Emp> findAll();

    void save(Emp emp);
}
