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
import com.librarymanagement.library.Entity.Book;
import com.librarymanagement.library.Service.BookService;

@RestController
@RequestMapping("/book")
public class BookContoller {

 @Autowired
 private BookService bookService;

 @PostMapping
 public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {
  return bookService.saveBook(book);
 }

 @GetMapping
 public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks() {

  return bookService.geAllBooks();
 }

 @GetMapping("/{id:\\d+}")
 public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable int id) {
  return bookService.getBookById(id);
 }

 @GetMapping("/{genre:[a-zA-Z][a-z]*}")
 public ResponseEntity<ResponseStructure<List<Book>>> getBookByGenre(@PathVariable String genre) {
  String normalizedGenre = genre.substring(0, 1).toUpperCase() + genre.substring(1);
  return bookService.getBookByGenre(normalizedGenre);
 }

 @PutMapping
 public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
  return bookService.updateBook(book);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<ResponseStructure<Book>> deleteBook(@PathVariable int id) {
  return bookService.deleteBookById(id);

 }

}
