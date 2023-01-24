package com.myhospitalapp.rest.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.myhospitalapp.rest.main.model.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer>{


}
