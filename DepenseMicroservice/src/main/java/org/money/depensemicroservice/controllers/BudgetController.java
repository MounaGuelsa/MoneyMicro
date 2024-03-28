//package org.money.depensemicroservice.controllers;
//
//import org.money.depensemicroservice.dtos.BudgetDto;
//import org.money.depensemicroservice.services.BudgetService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/budgets")
//public class BudgetController {
//
//    private final BudgetService budgetService;
//
//    @Autowired
//    public BudgetController(BudgetService budgetService) {
//        this.budgetService = budgetService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<BudgetDto>> obtenirBudgets()
//    {
//        try {
//            List<BudgetDto> budgets = budgetService.obtenirBudgets();
//            return new ResponseEntity<>(budgets, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BudgetDto> obtenirBudgetParId(@PathVariable Long id) {
//        try {
//            BudgetDto budget = budgetService.obtenirBudgetParId(id);
//            if (budget != null) {
//                return new ResponseEntity<>(budget, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<BudgetDto> ajouterBudget(@RequestBody BudgetDto budgetDto) {
//        try {
//            BudgetDto budget = budgetService.ajouterBudget(budgetDto);
//            return new ResponseEntity<>(budget, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BudgetDto> modifierBudget(@PathVariable Long id, @RequestBody BudgetDto budgetDto) {
//        try {
//            BudgetDto budget = budgetService.modifierBudget(id, budgetDto);
//            if (budget != null) {
//                return new ResponseEntity<>(budget, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> supprimerBudget(@PathVariable Long id) {
//        try {
//            budgetService.supprimmerBudget(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
