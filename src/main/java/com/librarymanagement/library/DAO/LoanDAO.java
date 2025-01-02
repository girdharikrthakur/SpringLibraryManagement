package com.librarymanagement.library.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.library.Entity.Loan;

@Repository
public interface LoanDAO extends JpaRepository<Loan, Integer> {

}