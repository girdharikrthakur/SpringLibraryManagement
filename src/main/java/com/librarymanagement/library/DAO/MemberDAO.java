package com.librarymanagement.library.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.librarymanagement.library.Entity.Member;
import com.librarymanagement.library.Repository.MemberRepository;

@Repository
public class MemberDAO {

 @Autowired
 private MemberRepository memberRepository;

 public Member saveMember(Member member) {
  return memberRepository.save(member);
 }

 public List<Member> getAllMembers() {
  return memberRepository.findAll();
 }

 public Optional<Member> getMemberById(int id) {

  return memberRepository.findById(id);
 }

 public Member updateMember(Member member) {
  return memberRepository.save(member);
 }

 public Member deleteMember(Member member) {

  memberRepository.delete(member);
  return null;
 }

}
