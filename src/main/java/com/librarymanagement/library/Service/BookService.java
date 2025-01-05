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

  if (books == null || books.isEmpty()) { // Check if books list is null or empty
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("No books found");
   structure.setData(null); // Explicitly set data to null
   return new ResponseEntity<>(structure, HttpStatus.NOT_FOUND);
  } else {
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Books found");
   structure.setData(books);
   return new ResponseEntity<>(structure, HttpStatus.OK);
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

 // PutMapping to updatebook by id

 public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {
  ResponseStructure<Book> structure = new ResponseStructure<>();

  // Check if the book already exists
  Optional<Book> existingBookOptional = bookDAO.getBookById(book.getId());

  if (existingBookOptional.isPresent()) {
   // If the book exists, update its fields
   Book existingBook = existingBookOptional.get();

   // Update only the fields that are provided
   if (book.getTitle() != null && !book.getTitle().isEmpty()) {
    existingBook.setTitle(book.getTitle());
   }
   if (book.getGenre() != null && !book.getGenre().isEmpty()) {
    existingBook.setGenre(book.getGenre());
   }
   // Add more fields as necessary

   // Save the updated book
   Book updatedBook = bookDAO.updateBook(existingBook); // Assuming updateBook saves the changes
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Book Updated");
   structure.setData(updatedBook);
   return new ResponseEntity<>(structure, HttpStatus.OK);
  } else {
   // If the book does not exist, create a new book
   Book savedBook = bookDAO.saveBook(book); // Assuming saveBook persists the book
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

 public ResponseEntity<ResponseStructure<List<Book>>> getBookByGenre(String genre) {
  List<Book> requestedBook = bookDAO.getBookByGenre(genre);
  ResponseStructure<List<Book>> structure = new ResponseStructure<>();
  if (requestedBook != null) {
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Book Found");
   structure.setData(requestedBook);
   return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.OK);

  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Book Not Found");
   return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.NOT_FOUND);
  }

 }
}
