package com.librarymanagement.library.DTO;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.librarymanagement.library.Entity.Loan;

public class ResponseStructure<T> {
 private int statuscode;
 private String message;
 private T data;

 public int getStatuscode() {
  return statuscode;
 }

 public void setStatuscode(int statuscode) {
  this.statuscode = statuscode;
 }

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

 public T getData() {
  return data;
 }

 public void setData(T data) {
  this.data = data;
 }

 public void setStatuscode(HttpStatus accepted) {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'setStatuscode'");
 }

 public void setData(List<Loan> loans) {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'setData'");
 }

 public void setData(ResponseEntity<ResponseStructure<Loan>> savedLoan) {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'setData'");
 }

 public void getData(Optional<Loan> requestedLoan) {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'getData'");
 }

}
