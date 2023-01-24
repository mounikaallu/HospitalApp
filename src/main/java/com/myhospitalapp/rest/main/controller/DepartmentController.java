package com.myhospitalapp.rest.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhospitalapp.rest.main.model.Department;
import com.myhospitalapp.rest.main.service.DepartmentService;


@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/add")
	public ResponseEntity<String> postDepartment(@RequestBody Department department) {
		departmentService.insertDepartment(department);
		return ResponseEntity.status(HttpStatus.OK).body("Department Posted in DB");
	}

}
