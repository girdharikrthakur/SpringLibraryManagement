package com.librarymanagement.library.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.library.DTO.ResponseStructure;
import com.librarymanagement.library.Entity.Member;
import com.librarymanagement.library.Service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
 @Autowired
 private MemberService memberService;

 @PostMapping
 public ResponseEntity<ResponseStructure<Member>> saveMember(@RequestBody Member member) {
  return memberService.saveMember(member);
 }

 @GetMapping
 public ResponseEntity<ResponseStructure<List<Member>>> getAllMembers(@RequestBody Member member) {
  return memberService.getAllMembers(member);
 }

 @GetMapping("/{id}")
 public ResponseEntity<ResponseStructure<Member>> getMemberById(@PathVariable int id) {
  return memberService.getMemberById(id);

 }

 @PutMapping
 public ResponseEntity<ResponseStructure<Member>> updateMember(@RequestBody Member member) {
  return memberService.updateMember(member);
 }

 @DeleteMapping
 public ResponseEntity<ResponseStructure<Member>> deleteMember(@RequestBody Member member) {
  return memberService.deleteMember(member);
 }

}