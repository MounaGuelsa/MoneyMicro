package org.money.depensemicroservice.controllers;

import org.money.depensemicroservice.dtos.FactureDto;
import org.money.depensemicroservice.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/factures")
public class FactureController {

    private final FactureService factureService;
    @Autowired

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping
    public ResponseEntity<List<FactureDto>> obtenirFactures() {
        List<FactureDto> factures = factureService.obtenirFactures();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDto> obtenirFactureParId(@PathVariable Long id) {
        FactureDto facture = factureService.obtenirFactureParId(id);
        if (facture != null) {
            return ResponseEntity.ok(facture);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureDto> modifierFacture(@PathVariable Long id, @RequestBody FactureDto factureDto) {
        FactureDto facture = factureService.modifierFacture(id, factureDto);
        if (facture != null) {
            return ResponseEntity.ok(facture);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerFacture(@PathVariable Long id) {
        factureService.supprimerFacture(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<FactureDto> upload (@RequestParam ( "multipartFile" ) MultipartFile multipartFile , @RequestParam("nomFacture") String nomFacture) {
        FactureDto factureDto = FactureDto.createFactureDto(null, nomFacture,  null);
        FactureDto facture= factureService.importerFacture(multipartFile, factureDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(facture);
    }

}