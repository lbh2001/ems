package com.lbh.controller;

import com.lbh.client.FileClient;
import com.lbh.entity.Emp;
import com.lbh.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lbh
 * @Date 2021/3/28
 * @Description:
 */

@RestController
@Slf4j
public class EmpController {


    @Autowired
    private EmpService empService;

    @Autowired
    private FileClient fileClient;

    @GetMapping("/emp/findAll")
    public List<Emp> findAll(){
        return empService.findAll();
    }

    @PostMapping("/emp/save")
    public Map<String,Object> save(Emp emp, MultipartFile photo){
        Map<String,Object> map = new HashMap<>();
        try{
            Map<String, Object> result = fileClient.upload(photo);
            log.info(result.toString());
            if(result.get("state").equals("false")) throw new RuntimeException("fail!!");
            //持久化到数据库
            emp.setPath(result.get("url").toString());
            empService.save(emp);
            map.put("msg","保存成功");
            map.put("state",true);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","保存失败");
            map.put("state",false);
        }
        return map;
    }

}
