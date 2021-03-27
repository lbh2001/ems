package com.lbh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author lbh
 * @Date 2021/3/26
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lbh.dao")
public class UserserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserserverApplication.class,args);
    }
}
