package com.librarymanagement.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.library.DAO.AuthorDAO;
import com.librarymanagement.library.DTO.ResponseStructure;
import com.librarymanagement.library.Entity.Author;

@Service
public class AuthorService {
 @Autowired
 private AuthorDAO authorDAO;

 // PostMapping to Save Data to DataBase

 public ResponseEntity<ResponseStructure<Author>> saveAuthor(Author author) {
  Author recieveAuthor = authorDAO.saveAuthor(author);

  ResponseStructure<Author> structure = new ResponseStructure<>();
  structure.setStatuscode(HttpStatus.CREATED.value());
  structure.setMessage("Author saved successfully");
  structure.setData(recieveAuthor);
  return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.CREATED);
 }

 // GetMapping to GetAllAuthor

 public ResponseEntity<ResponseStructure<List<Author>>> getAllAuthor() {

  List<Author> authors = authorDAO.getAllAuthor();
  ResponseStructure<List<Author>> structure = new ResponseStructure<>();

  if (authors==null|| authors.isEmpty()) {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("No Author Exist");
   return new ResponseEntity<ResponseStructure<List<Author>>>(structure, HttpStatus.NOT_FOUND);

  }
  else {
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Authors retrieved successfully");
   structure.setData(authors);
   return new ResponseEntity<ResponseStructure<List<Author>>>(structure, HttpStatus.OK);
  }
 }

 // GetMapping to request Author by Id

 public ResponseEntity<ResponseStructure<Author>> getAuthorById(int id) {
  Optional<Author> author = authorDAO.getAuthorById(id);
  if (author.isEmpty()) {
   ResponseStructure<Author> structure = new ResponseStructure<>();
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Author not found");
   return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.NOT_FOUND);
  }

  ResponseStructure<Author> structure = new ResponseStructure<>();
  structure.setStatuscode(HttpStatus.OK.value());
  structure.setMessage("Author retrieved successfully");
  structure.setData(author.get());
  return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.OK);
 }

 // PutMapping to update Author

 public ResponseEntity<ResponseStructure<Author>> updateAuthor(int id, Author updateAuthorDetails) {
  ResponseStructure<Author> structure = new ResponseStructure<>();

  if (authorDAO.getAuthorById(id).isPresent()) {
   Author author = authorDAO.getAuthorById(id).get();
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Author Already Exist");
   structure.setData(author);
   return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.OK);

  }

  Author updatedAuthor = authorDAO.updateAuthor(updateAuthorDetails);
  structure.setStatuscode(HttpStatus.OK.value());
  structure.setMessage("Author updated successfully");
  structure.setData(updatedAuthor);
  return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.OK);

 }

 // DeleteMapping to Delete Author by Id
 public ResponseEntity<ResponseStructure<Author>> deleteAuthor(int id) {
  ResponseStructure<Author> structure = new ResponseStructure<>();

  Optional<Author> rquestedAuthor = authorDAO.getAuthorById(id);

  if (rquestedAuthor.isPresent()) {
   Author deletedAuthor = authorDAO.deleteAuthor(id);

   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Author deleted successfully");
   structure.setData(deletedAuthor);
   return new ResponseEntity<ResponseStructure<Author>>(structure, HttpStatus.OK);
  }

  else {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Author not found");
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

 }

}
