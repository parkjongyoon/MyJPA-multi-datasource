package com.example.myjpa2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.myjpa2.entity.Department;
import com.example.myjpa2.entity.QDepartment;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class DepartmentRepositoryCustomImpl implements DepartmentRepositoryCustom {

	@Autowired
	private JPAQueryFactory secondJpaQueryFactory;
	private QDepartment qDepartment = QDepartment.department;
	
	@Override
	public List<Department> findByName(String keyword) {
		
		List<Department> content = secondJpaQueryFactory
				.select(qDepartment)
				.from(qDepartment)
				.where(qDepartment.name.contains(keyword))
				.fetch();
		return content;
	}
}
