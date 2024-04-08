package org.money.feignclient.Revenue;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
@FeignClient("Revenue")
public interface RevenueClient {
    @GetMapping("/revenues/total")
    Double totalRevenuesParMois();
}

