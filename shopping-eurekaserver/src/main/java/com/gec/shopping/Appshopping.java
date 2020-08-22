package com.gec.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Appshopping {
    public static void main(String[] args) {
        SpringApplication.run(Appshopping.class,args);
    }
}
