package com.librarymanagement.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.library.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
