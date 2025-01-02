package com.librarymanagement.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.librarymanagement.library.DAO.MemberDAO;
import com.librarymanagement.library.DTO.ResponseStructure;
import com.librarymanagement.library.Entity.Member;

@Service
public class MemberService {

 @Autowired
 private MemberDAO memberDAO;

 // PostMapping to Save Members
 public ResponseEntity<ResponseStructure<Member>> saveMember(Member member) {
  Member savedMember = memberDAO.saveMember(member);
  ResponseStructure<Member> structure = new ResponseStructure<>();
  structure.setStatuscode(HttpStatus.CREATED.value());
  structure.setMessage("Member saved successfully");
  structure.setData(savedMember);
  return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.CREATED);

 }

 // GetMapping To Get All the Books

 public ResponseEntity<ResponseStructure<List<Member>>> getAllMembers(Member member) {
  List<Member> Members = memberDAO.getAllMembers();
  ResponseStructure<List<Member>> structure = new ResponseStructure<>();
  structure.setStatuscode(HttpStatus.OK.value());
  structure.setMessage("Books retrieved successfully");
  structure.setData(Members);
  return new ResponseEntity<ResponseStructure<List<Member>>>(structure, HttpStatus.ACCEPTED);
 }

 public ResponseEntity<ResponseStructure<Member>> getMemberById(int id) {
  Optional<Member> requestedMember = memberDAO.getMemberById(id);
  ResponseStructure<Member> structure = new ResponseStructure<>();
  if (requestedMember.isPresent()) {
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Member retrieved successfully");
   structure.setData(requestedMember.get());
   return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.OK);
  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Member not found");
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

 }

 public ResponseEntity<ResponseStructure<Member>> updateMember(Member member) {

  Optional<Member> requestedMember = memberDAO.getMemberById(member.getId());
  ResponseStructure<Member> structure = new ResponseStructure<>();
  if (requestedMember.isPresent()) {
   Member updatedMember = memberDAO.updateMember(member);
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Member updated successfully");
   structure.setData(updatedMember);
   return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.OK);
  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Member not found");
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

 }

 public ResponseEntity<ResponseStructure<Member>> deleteMember(Member member) {

  Optional<Member> requestedMember = memberDAO.getMemberById(member.getId());
  ResponseStructure<Member> structure = new ResponseStructure<>();
  if (requestedMember.isPresent()) {
   Member deletedMember = memberDAO.deleteMember(member);
   structure.setStatuscode(HttpStatus.OK.value());
   structure.setMessage("Member deleted successfully");
   structure.setData(deletedMember);
   return new ResponseEntity<ResponseStructure<Member>>(structure, HttpStatus.OK);
  } else {
   structure.setStatuscode(HttpStatus.NOT_FOUND.value());
   structure.setMessage("Member not found");
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

 }

}
