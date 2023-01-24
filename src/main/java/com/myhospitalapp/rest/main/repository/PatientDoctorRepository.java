package com.myhospitalapp.rest.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.myhospitalapp.rest.main.model.PatientDoctor;

public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer>  {
	

}
