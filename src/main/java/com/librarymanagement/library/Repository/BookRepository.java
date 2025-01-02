package com.librarymanagement.library.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.library.Entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

 List<Book> findByGenre(String genre);

}
