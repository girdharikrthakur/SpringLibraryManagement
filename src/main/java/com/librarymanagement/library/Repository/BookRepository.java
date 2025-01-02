package com.librarymanagement.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.library.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
