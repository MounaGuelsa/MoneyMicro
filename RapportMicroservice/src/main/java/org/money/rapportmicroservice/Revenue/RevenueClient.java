package org.money.rapportmicroservice.Revenue;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("Revenue")
public interface RevenueClient {
    @GetMapping("/revenues/total")
    Double totalRevenuesParMois();
}

