package com.example.myjpa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myjpa2.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>, DepartmentRepositoryCustom {

}
