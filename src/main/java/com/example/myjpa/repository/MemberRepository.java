package com.example.myjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myjpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {

}
