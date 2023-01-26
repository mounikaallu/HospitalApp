package com.myhospitalapp.rest.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myhospitalapp.rest.main.model.PatientAdmit;
import com.myhospitalapp.rest.main.repository.PatientAdmitRepository;
@Service
public class PatientAdmitService {

	@Autowired
    private PatientAdmitRepository patientAdmitRepository;

    public void insert(PatientAdmit patientAdmit) {
    	patientAdmitRepository.save(patientAdmit);
    }

	public List<PatientAdmit> getAllPatientAdmit() {
		
		return patientAdmitRepository.findAll();
	}

	public Optional<PatientAdmit> getPatientAdmitById(int id) {
		Optional<PatientAdmit> optional=patientAdmitRepository.findById(id);
		return optional;
	}

	

	public void deletePatientAdmitById(int id) {
		patientAdmitRepository.deleteById(id);
		
	}

	public void postAdmittedPatient(PatientAdmit patientAdmit) {
		patientAdmitRepository.save(patientAdmit);
		
	}

	
}
