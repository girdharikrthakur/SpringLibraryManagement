package com.librarymanagement.library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.library.DTO.ResponseStructure;
import com.librarymanagement.library.Entity.Loan;
import com.librarymanagement.library.Service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController {

 @Autowired
 private LoanService loanService;

 @PostMapping
 public ResponseEntity<ResponseStructure<Loan>> saveLoan(@RequestBody Loan loan) {
  return loanService.saveLoan(loan);
 }

 @GetMapping
 public ResponseEntity<ResponseStructure<List<Loan>>> getLoans() {
  return loanService.getLoans();
 }

 @GetMapping("/{id}")
 public ResponseEntity<ResponseStructure<Loan>> getLoanById(@PathVariable int id) {

  return loanService.getLoanById(id);
 }

 @PutMapping
 public ResponseEntity<ResponseStructure<Loan>> updateLoan(@RequestBody Loan loan) {
  return loanService.updateLoan(loan);
 }


 @DeleteMapping("/{id}")
 public ResponseEntity<ResponseStructure<Loan>> deleteLoanById(@PathVariable int id) {
  return loanService.deleteLoanById(id);
 }
}