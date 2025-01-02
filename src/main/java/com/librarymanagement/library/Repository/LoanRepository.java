package com.librarymanagement.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.library.Entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

}
