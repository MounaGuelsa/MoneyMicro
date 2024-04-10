package org.money.depensemicroservice.controllers;

import org.money.depensemicroservice.dtos.CategorieDto;
import org.money.depensemicroservice.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categories")
public class CategorieController {

    private final CategorieService categorieService;
    @Autowired

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }
    @GetMapping
    public ResponseEntity<List<CategorieDto>> obtenirCategories() {
        List<CategorieDto> categories = categorieService.obtenirCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategorieDto> obtenirCategorieParId(@PathVariable Long id) {
        CategorieDto categorie = categorieService.obtenirCategorieParId(id);
        if (categorie != null) {
            return ResponseEntity.ok(categorie);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CategorieDto> ajouterCategorie(@RequestBody CategorieDto categorieDto) {
        CategorieDto categorie = categorieService.ajouterCategorie(categorieDto);
        return  new ResponseEntity<>(categorie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieDto> modifierCategorie(@PathVariable Long id, @RequestBody CategorieDto categorieDto) {
        CategorieDto categorie = categorieService.modifierCategorie(id, categorieDto);
        if (categorie != null) {
            return ResponseEntity.ok(categorie);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCategorie(@PathVariable Long id) {
        categorieService.supprimerCategorie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
