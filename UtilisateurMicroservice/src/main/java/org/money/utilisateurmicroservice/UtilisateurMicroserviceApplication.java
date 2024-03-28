package org.money.utilisateurmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.money.utilisateurmicroservice")

public class UtilisateurMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilisateurMicroserviceApplication.class, args);
    }

}
