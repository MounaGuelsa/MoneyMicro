package org.money.utilisateurmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class UtilisateurMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilisateurMicroserviceApplication.class, args);
    }

}
