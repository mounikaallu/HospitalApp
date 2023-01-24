package com.myhospitalapp.rest.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhospitalapp.rest.main.model.Department;
import com.myhospitalapp.rest.main.repository.DepartmentRepository;


@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;


	public void insertDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentRepository.save(department);
	}

	
}
