package com.example.myjpa.repository;

import java.util.List;

import com.example.myjpa.entity.Member;

public interface MemberRepositoryCustom {

	List<Member> findByNameOrEmail(String type, String keyword);
}
