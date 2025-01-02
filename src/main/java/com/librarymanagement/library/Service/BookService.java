package com.librarymanagement.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.library.DAO.BookDAO;
import com.librarymanagement.library.DTO.ResponseStructure;
import com.librarymanagement.library.Entity.Book;

@Service
public class BookService {

 @Autowired
 private BookDAO bookDAO;

 // PostMapping To save Books

 public ResponseEntity<ResponseStructure<Book>> saveBook(Book book) {
  Book savedBook = bookDAO.saveBook(book);

  ResponseStructure<Book> structure = new ResponseStructure<>();
  structure.setStatuscode(HttpStatus.CREATED.value());
  structure.setMessage("Book Saved");
  structure.setData(savedBook);
  return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.CREATED);
 }

 // GetMapping to get all the books

 public ResponseEntity<ResponseStructure<List<Book>>> geAllBooks() {

  List<Book> books = bookDAO.getAllBooks();

  ResponseStructure<List<Book>> structure = new ResponseStructure<>();

  if (books.isEmpty()) {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("No book Found");
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  } else {

   structure.setStatuscode(HttpStatus.FOUND.value());
   structure.setMessage("Found Books");
   structure.setData(books);
   return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.FOUND);
  }

 }

 // GetMapping to gt book by id

 public ResponseEntity<ResponseStructure<Book>> getBookById(int id) {
  Optional<Book> book = bookDAO.getBookById(id);

  ResponseStructure<Book> structure = new ResponseStructure<>();

  if (book.isPresent()) {
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Book Found");
   structure.setData(book.get());
   return new ResponseEntity<>(structure, HttpStatus.OK);
  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Book Not Found");
   return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);

  }

 }

 // PutMappingy to updatebook by id

 public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {

  Optional<Book> existigBook = bookDAO.getBookById(book.getId());
  ResponseStructure<Book> structure = new ResponseStructure<>();

  if (existigBook.isPresent()) {
   Book updatedBook = bookDAO.updateBook(book);
   structure.setStatuscode(HttpStatus.ACCEPTED.value());
   structure.setMessage("Book Updated");
   structure.setData(updatedBook);
   return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);

  } else {
   Book savedBook = bookDAO.updateBook(book);
   structure.setStatuscode(HttpStatus.CREATED.value());
   structure.setMessage("Book Created");
   structure.setData(savedBook);
   return new ResponseEntity<>(structure, HttpStatus.CREATED);
  }

 }

 // DeleteMappling to delete book by id

 public ResponseEntity<ResponseStructure<Book>> deleteBookById(int id) {

  Optional<Book> bookExist = bookDAO.getBookById(id);

  ResponseStructure<Book> structure = new ResponseStructure<>();

  if (bookExist.isPresent()) {
   Book deletedbook = bookDAO.deleteBookById(id);
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Book Deleted");
   structure.setData(deletedbook);
   return new ResponseEntity<>(structure, HttpStatus.OK);

  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Book Not Found");
   return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
  }

 }
}
