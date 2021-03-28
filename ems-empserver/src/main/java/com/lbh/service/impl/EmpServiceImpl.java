package com.lbh.service.impl;

import com.lbh.dao.EmpDao;
import com.lbh.entity.Emp;
import com.lbh.service.EmpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lbh
 * @Date 2021/3/28
 * @Description:
 */
@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Resource
    private EmpDao empDao;

    @Override
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    @Override
    public void save(Emp emp) {
        empDao.save(emp);
    }
}
