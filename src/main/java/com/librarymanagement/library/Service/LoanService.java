package com.librarymanagement.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.library.DAO.LoanDAO;
import com.librarymanagement.library.DTO.ResponseStructure;
import com.librarymanagement.library.Entity.Loan;

@Service
public class LoanService {

 @Autowired
 private LoanDAO loanDAO;

 public ResponseEntity<ResponseStructure<Loan>> saveLoan(Loan loan) {

  Loan savedLoan = loanDAO.saveLoan(loan);

  ResponseStructure<Loan> structure = new ResponseStructure<>();
  structure.setStatuscode(HttpStatus.CREATED.value());
  structure.setMessage("Loan saved successfully");
  structure.setData(savedLoan);
  return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.CREATED);

 }

 public ResponseEntity<ResponseStructure<List<Loan>>> getLoans() {

  List<Loan> loans = loanDAO.getLoans();
  ResponseStructure<Loan> structure = new ResponseStructure<>();
  structure.setStatuscode(HttpStatus.ACCEPTED.value());
  structure.setMessage("Loans retrieved successfully");
  structure.setData(loans);
  return new ResponseEntity<ResponseStructure<List<Loan>>>(HttpStatus.ACCEPTED);

 }

 public ResponseEntity<ResponseStructure<Loan>> getLoanById(int id) {

  Optional<Loan> requestedLoan = loanDAO.getLoanById(id);
  ResponseStructure<Loan> structure = new ResponseStructure<>();
  if (requestedLoan.isPresent()) {
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Loan retrieved successfully");
   structure.getData(requestedLoan);
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.OK);
  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND);
   structure.setMessage("Id Not Found");
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.NOT_FOUND);
  }
 }

 public ResponseEntity<ResponseStructure<Loan>> updateLoan(Loan loan) {
  Optional<Loan> existingLoan = loanDAO.getLoanById(loan.getId());
  ResponseStructure<Loan> structure = new ResponseStructure<>();
  if (existingLoan.isPresent()) {

   Loan updatedLoan = loanDAO.updateLoan(loan);
   structure.setStatuscode(HttpStatus.ACCEPTED);
   structure.setMessage("Loan Updated");
   structure.setData(updatedLoan);
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.ACCEPTED);
  }

  else {
   Loan savedLoan = loanDAO.updateLoan(loan);
   structure.setStatuscode(HttpStatus.CREATED.value());
   structure.setMessage("Loan Saved");
   structure.setData(savedLoan);
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.CREATED);

  }

 }

 public ResponseEntity<ResponseStructure<Loan>> deleteLoanById(int id) {

  Optional<Loan> loanExist = loanDAO.getLoanById(id);
  ResponseStructure<Loan> structure = new ResponseStructure<>();
  if (loanExist.isPresent()) {
   Loan deletedLoan = loanDAO.deleteLoanById(id);
   structure.setStatuscode(HttpStatus.ACCEPTED.value());
   structure.setMessage("Loan Deleted");
   structure.setData(deletedLoan);
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.ACCEPTED);

  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND);
   structure.setMessage("Loan ID Not Found");
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.NOT_FOUND);
  }

 }

}
