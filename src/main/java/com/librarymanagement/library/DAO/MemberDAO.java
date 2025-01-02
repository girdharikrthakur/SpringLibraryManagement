package com.librarymanagement.library.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarymanagement.library.Entity.Member;

@Repository
public interface MemberDAO extends JpaRepository<Member, Integer> {

}