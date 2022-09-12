package com.example.myjpa.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myjpa.entity.Member;
import com.example.myjpa.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

	@Autowired
	private JPAQueryFactory defaultJpaQueryFactory;
	private QMember qMember = QMember.member;
	
	@Override
	public List<Member> findByNameOrEmail(String type, String keyword) {
		
		List<Member> content = defaultJpaQueryFactory
				.select(qMember)
				.from(qMember)
				.where(qMember.email.contains(keyword))
				.fetch();
		return content;
	}
}
