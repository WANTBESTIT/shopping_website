package com.gec.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.gec.shopping.org.gec.security.mapper")
public class AppManagerMS {

    public static void main(String[] args) {

        SpringApplication.run(AppManagerMS.class,args);

    }
}
