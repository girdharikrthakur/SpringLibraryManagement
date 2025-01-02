package com.librarymanagement.library.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.librarymanagement.library.Entity.Book;
import com.librarymanagement.library.Repository.BookRepository;

@Repository
public class BookDAO {

 @Autowired
 private BookRepository bookRepository;

 public Book saveBook(Book book) {
  return bookRepository.save(book);
 }

 public List<Book> getAllBooks() {
  return bookRepository.findAll();
 }

 public Optional<Book> getBookById(int id) {
  return bookRepository.findById(id);
 }

 public Book updateBook(Book book) {
  return bookRepository.save(book);
 }

 public Book deleteBookById(int id) {
  bookRepository.deleteById(id);
  return null;
 }

}
