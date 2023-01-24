package com.myhospitalapp.rest.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhospitalapp.rest.main.model.Department;
import com.myhospitalapp.rest.main.repository.DepartmentRepository;
@Service

public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	

	

	public List<Department> getAllDepartment() {
		
		return departmentRepository.findAll();
	}

	public Optional<Department> getDepartmentById(int id) {
		Optional<Department> optional=departmentRepository.findById(id);
        return optional;
		
	}

	public Optional<Department> deleteDepartmentById(int id) {
		Optional<Department> optional=departmentRepository.findById(id);
        return optional;
		
	
	}

}
