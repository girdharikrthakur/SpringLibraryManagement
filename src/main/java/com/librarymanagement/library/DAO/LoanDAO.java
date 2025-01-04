package com.librarymanagement.library.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.librarymanagement.library.Entity.Loan;
import com.librarymanagement.library.Repository.LoanRepository;

@Repository
public class LoanDAO {

 @Autowired
 private LoanRepository loanRepository;

 public Loan saveLoan(Loan loan) {
  return loanRepository.save(loan);
 }

 public List<Loan> getLoans() {
  return loanRepository.findAll(); // Ensure the loans are fetched with associated Book and Member
 }

 public Optional<Loan> getLoanById(int id) {
  return loanRepository.findById(id);
 }

 public Loan updateLoan(Loan loan) {

  return loanRepository.save(loan);
 }

 public Loan deleteLoanById(int id) {

  loanRepository.deleteById(id);
  return null;
 }

}