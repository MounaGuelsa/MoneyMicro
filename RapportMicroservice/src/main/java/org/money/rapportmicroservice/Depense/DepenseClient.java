package org.money.rapportmicroservice.Depense;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("Depense")
public interface DepenseClient {

@GetMapping("/depenses/total")
Double totalDepensesParMois();
}

