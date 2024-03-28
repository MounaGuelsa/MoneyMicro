package org.money.feignclient.depense;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("DEPENSE")
public interface DepenseClient {


    @PostMapping("/depenses")
    ResponseEntity<DepenseDto> ajouterDepense(@RequestBody @Valid DepenseDto depense);


    @GetMapping("/depenses/{id}")
    ResponseEntity<DepenseDto> obtenirDepenseParId(@PathVariable Long id);

}
