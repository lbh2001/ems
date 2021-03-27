package com.lbh;

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
public class EmpserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpserverApplication.class,args);
    }
}
