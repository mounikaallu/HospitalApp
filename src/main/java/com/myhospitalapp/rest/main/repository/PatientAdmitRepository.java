package com.myhospitalapp.rest.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myhospitalapp.rest.main.model.PatientAdmit;

public interface PatientAdmitRepository extends JpaRepository<PatientAdmit, Integer> {

	

}
