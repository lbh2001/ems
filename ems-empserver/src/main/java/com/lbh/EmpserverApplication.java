package com.lbh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author lbh
 * @Date 2021/3/26
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lbh.dao")
@EnableFeignClients
public class EmpserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpserverApplication.class,args);
    }
}
