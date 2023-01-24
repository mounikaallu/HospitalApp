package com.myhospitalapp.rest.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	

	@GetMapping("/getall")
	public List<Department> getAllDepartment() {
		List<Department> list =departmentService.getAllDepartment();
return list;
	}
	@GetMapping("/one/{id}")
	public ResponseEntity<Object> getDepartmentById(@PathVariable("id")int id) {
		Optional<Department> optional =departmentService.getDepartmentById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		}
		Department department = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(department);
		}
	@DeleteMapping("/two/{id}")
	public ResponseEntity<Object> deleteDepartmentById(@PathVariable("id")int id){
		Optional<Department> optional = departmentService.deleteDepartmentById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		}
		Department department = optional.get();
	    return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
		
	}
	
	
	

}
