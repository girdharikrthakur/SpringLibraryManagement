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
import com.librarymanagement.library.Entity.Author;
import com.librarymanagement.library.Service.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

 @Autowired
 private AuthorService authorService;

 @PostMapping
 public ResponseEntity<ResponseStructure<Author>> saveAuthor(@RequestBody Author author) {

  return authorService.saveAuthor(author);
 }

 @GetMapping
 public ResponseEntity<ResponseStructure<List<Author>>> getAllAuthor(@RequestBody Author author) {
  return authorService.getAllAuthor();
 }

 @GetMapping("/{id}")
 public ResponseEntity<ResponseStructure<Author>> getAuthorByid(@PathVariable int id) {
  return authorService.getAuthorById(id);
 }

 @PutMapping
 public ResponseEntity<ResponseStructure<Author>> updateAuthor(@RequestBody Author author) {
  return authorService.updateAuthor(author);

 }

 @DeleteMapping("/{id}")
 public ResponseEntity<ResponseStructure<Author>> deleteAuthor(@PathVariable int id) {
  return authorService.deleteAuthor(id);
 }
}