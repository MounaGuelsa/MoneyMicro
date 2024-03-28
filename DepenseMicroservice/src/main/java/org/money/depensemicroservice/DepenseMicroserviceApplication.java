package org.money.depensemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.money")
public class DepenseMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepenseMicroserviceApplication.class, args);
    }

}
