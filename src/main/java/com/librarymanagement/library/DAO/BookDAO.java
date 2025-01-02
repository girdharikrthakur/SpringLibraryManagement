package com.librarymanagement.library.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.library.Entity.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

}
