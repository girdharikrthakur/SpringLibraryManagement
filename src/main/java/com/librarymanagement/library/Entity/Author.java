package com.librarymanagement.library.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Author {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String authorName;

 @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
 @JsonIgnore
 private List<Book> books;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getAuthor() {
  return authorName;
 }

 public void setAuthor(String authorName) {
  this.authorName = authorName;
 }

 public List<Book> getBooks() {
  return books;
 }

 public void setBooks(List<Book> books) {
  this.books = books;
 }

}