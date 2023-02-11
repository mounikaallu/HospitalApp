package com.myhospitalapp.rest.main.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myhospitalapp.rest.main.model.Doctor;
import com.myhospitalapp.rest.main.model.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer>{

	


	List<Patient> getByDoctorId(int did);

	

	


}
