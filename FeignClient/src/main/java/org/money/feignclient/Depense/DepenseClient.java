package org.money.feignclient.Depense;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DepenseClient {
    @GetMapping("/depenses")
    ResponseEntity<List<DepenseDto>> obtenirDepenses();

    @PostMapping("/depenses")
    ResponseEntity<DepenseDto> ajouterDepense(@RequestBody DepenseDto depenseDto);

}
