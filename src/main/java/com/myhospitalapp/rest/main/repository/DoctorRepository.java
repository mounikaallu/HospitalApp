package com.myhospitalapp.rest.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myhospitalapp.rest.main.model.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

   List<Doctor> getByDepartmentId(int depid);

}
