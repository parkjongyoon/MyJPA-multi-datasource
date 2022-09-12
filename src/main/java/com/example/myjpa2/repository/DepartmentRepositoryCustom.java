package com.example.myjpa2.repository;

import java.util.List;

import com.example.myjpa2.entity.Department;

public interface DepartmentRepositoryCustom {

	List<Department> findByName(String keyword);
}
