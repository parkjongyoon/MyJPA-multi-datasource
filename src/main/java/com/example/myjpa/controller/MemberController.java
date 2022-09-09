package com.example.myjpa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myjpa.entity.Member;
import com.example.myjpa.repository.MemberRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MemberController {

	private MemberRepository memberRepository;
	
	@GetMapping("members")
	public ResponseEntity<List<Member>> selectMember(){
		List<Member> list = memberRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("members/search")
	public ResponseEntity<List<Member>> selectMemberByNameOrEmail(
			@RequestParam String type, 
			@RequestParam String keyword){
		List<Member> list = memberRepository.findByNameOrEmail(type, keyword);
		return ResponseEntity.ok(list);
	}
}
