package com.myhospitalapp.rest.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhospitalapp.rest.main.model.HospitalStaff;
import com.myhospitalapp.rest.main.model.PatientDoctor;
import com.myhospitalapp.rest.main.repository.PatientDoctorRepository;

@Service
public class PatientDoctorService {

	@Autowired
	private PatientDoctorRepository patientDoctorRepository;

	public void insert(PatientDoctor patientDoctor) {
		patientDoctorRepository.save(patientDoctor);
		
	}
	
//	public void insertPatientDoctor(PatientDoctor patientDoctor) {
//		patientDoctorRepository.save(patientDoctor);
//		
//	}
//
//	public List<PatientDoctor> getAllPatientDoctor() {
//		return patientDoctorRepository.findAll();
//	}
//
//	public Optional<PatientDoctor> getPatientDoctorById(int id) {
//		Optional<PatientDoctor> optional=patientDoctorRepository.findById(id);
//        return optional;
//	}
//	
//	public Optional<PatientDoctor> deletePatientDoctorById(int id) {
//		Optional<PatientDoctor> optional=patientDoctorRepository.findById(id);
//        return optional;
//	}

}
