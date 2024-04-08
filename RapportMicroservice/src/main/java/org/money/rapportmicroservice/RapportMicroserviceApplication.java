package org.money.rapportmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "org.money")
public class RapportMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RapportMicroserviceApplication.class, args);
    }

}
