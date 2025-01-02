package com.librarymanagement.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.library.Entity.Author;

 
public interface AuthorRepository extends JpaRepository<Author, Integer> {

 Author deleteAllById(int id);

}
