package com.librarymanagement.library.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Book {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String title;
 private String genre;

 @ManyToOne
 @JoinColumn(name = "author_id")
 private Author author;

 @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
 @JsonBackReference
 private List<Loan> loans;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getGenre() {
  return genre;
 }

 public void setGenre(String genre) {
  this.genre = genre;
 }

 public Author getAuthor() {
  return author;
 }

 public void setAuthor(Author author) {
  this.author = author;
 }

 public List<Loan> getLoans() {
  return loans;
 }

 public void setLoans(List<Loan> loans) {
  this.loans = loans;
 }

}
