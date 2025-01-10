package com.librarymanagement.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.library.DAO.BookDAO;
import com.librarymanagement.library.DAO.LoanDAO;
import com.librarymanagement.library.DAO.MemberDAO;
import com.librarymanagement.library.DTO.ResponseStructure;
import com.librarymanagement.library.Entity.Book;
import com.librarymanagement.library.Entity.Loan;
import com.librarymanagement.library.Entity.Member;

@Service
public class LoanService {

 @Autowired
 private LoanDAO loanDAO;

 @Autowired
 private BookDAO bookDAO;

 @Autowired
 private MemberDAO memberDAO;

 ///// PutMapping

 public ResponseEntity<ResponseStructure<Loan>> saveLoan(Loan loan) {

  ResponseStructure<Loan> structure = new ResponseStructure<>();

  // Validating associated Book
  Optional<Book> bookOptional = bookDAO.getBookById(loan.getBook().getId());
  if (bookOptional.isEmpty()) {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Book Not Found");
   structure.setData(null);
   return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
  }
  // Fetch the book if exists
  Book book = bookOptional.get();
  // Validating associated Member
  Optional<Member> memberOptional = memberDAO.getMemberById(loan.getMember().getId());
  if (memberOptional.isEmpty()) {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Member Not Found");
   structure.setData(null);
   return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
  }
  // Fetch the member if exists
  Member member = memberOptional.get();

  // Set the book and member to the loan
  loan.setBook(book);
  loan.setMember(member);

  Loan savedLoan = loanDAO.saveLoan(loan);

  structure.setStatuscode(HttpStatus.CREATED.value());
  structure.setMessage("Loan saved successfully");
  structure.setData(savedLoan);
  return new ResponseEntity<>(structure, HttpStatus.CREATED);
 }

 ////

 public ResponseEntity<ResponseStructure<List<Loan>>> getLoans() {
  List<Loan> loans = loanDAO.getLoans(); // Fetch the loans from the DAO layer
  ResponseStructure<List<Loan>> structure = new ResponseStructure<>();

  if (loans == null || loans.isEmpty()) {
   structure.setStatuscode(HttpStatus.NO_CONTENT.value());
   structure.setMessage("No Loans Found");
   structure.setData(null);
   return new ResponseEntity<>(structure, HttpStatus.NO_CONTENT);

  } else {
   structure.setStatuscode(HttpStatus.ACCEPTED.value());
   structure.setMessage("Loans retrieved successfully");
   structure.setData(loans); // Set the list of loans as data
   return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
  }
 }

 ////

 public ResponseEntity<ResponseStructure<Loan>> getLoanById(int id) {

  Optional<Loan> requestedLoan = loanDAO.getLoanById(id);
  ResponseStructure<Loan> structure = new ResponseStructure<>();
  if (requestedLoan.isPresent()) {

   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Loan retrieved successfully");
   structure.setData(requestedLoan.get());
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.OK);
  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Id Not Found");
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.NOT_FOUND);
  }
 }

 ////

 public ResponseEntity<ResponseStructure<Loan>> updateLoan(Loan loan) {
  Optional<Loan> existingLoan = loanDAO.getLoanById(loan.getId());
  ResponseStructure<Loan> structure = new ResponseStructure<>();
  if (existingLoan.isPresent()) {

   Loan updatedLoan = loanDAO.updateLoan(loan);
   structure.setStatuscode(HttpStatus.ACCEPTED.value());
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

 ////

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
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Loan ID Not Found");
   return new ResponseEntity<ResponseStructure<Loan>>(structure, HttpStatus.NOT_FOUND);
  }

 }

}
