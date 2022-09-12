package com.example.myjpa2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myjpa2.entity.Department;
import com.example.myjpa2.repository.DepartmentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
public class DepartmentController {

	private DepartmentRepository departmentRepository;
	
	@GetMapping("departments")
	public ResponseEntity<List<Department>> selectDepartment(){
		log.debug("aaaaaaaa");
		List<Department> list = departmentRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("departments/search")
	public ResponseEntity<List<Department>> selectDepartmentByName(@RequestParam String keyword){
		List<Department> list = departmentRepository.findByName(keyword);
		return ResponseEntity.ok(list);
	}
}
