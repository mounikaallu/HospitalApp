package com.myhospitalapp.rest.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myhospitalapp.rest.main.model.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
