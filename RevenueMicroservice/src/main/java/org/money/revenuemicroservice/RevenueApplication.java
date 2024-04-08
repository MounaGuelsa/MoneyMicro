package org.money.revenuemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "org.money")
public class RevenueApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevenueApplication.class, args);
    }

}
