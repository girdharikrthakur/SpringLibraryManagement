package com.librarymanagement.library.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.librarymanagement.library.Entity.Author;
import com.librarymanagement.library.Repository.AuthorRepository;

@Repository
public class AuthorDAO {

 @Autowired
 private AuthorRepository authorRepository;

 public Author saveAuthor(Author author) {
  return authorRepository.save(author);

 }

 public List<Author> getAllAuthor(Author author) {
  return authorRepository.findAll();
 }

 public Optional<Author> getAuthorById(int id) {
  return authorRepository.findById(id);
 }

 public Author updateAuthor(Author author) {
  return authorRepository.save(author);
 }

 public Author deleteAuthor(int id) {
  authorRepository.deleteById(id);

  return Author;

 }

}