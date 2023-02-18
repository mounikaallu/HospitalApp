package com.myhospitalapp.rest.main.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myhospitalapp.rest.main.model.HospitalStaff;
import com.myhospitalapp.rest.main.model.Patient;
import com.myhospitalapp.rest.main.repository.PatientRepository;

@Service

public class PatientService {
		@Autowired
		private PatientRepository patientRepository;

		public void insertPatient(Patient patient) {
			patientRepository.save(patient);	
		}

		public List<Patient> getAllPatients() {
			return patientRepository.findAll();
		}

		public Optional<Patient> getPatientById(int id) {
			Optional<Patient> optional=patientRepository.findById(id);
	        return optional;
		}

		
			

		public void deletePatientById(int id) {
			patientRepository.deleteById(id);
		}
		public void updatePatientById(Patient patient) {
			patientRepository.save(patient);
		}

		public List<Patient> getPatientByDoctorId(int did) {
			return patientRepository.getByDoctorId(did);
			
		}
		public void postPatient(Patient patient) {
			// save employee in DB 
			patientRepository.save(patient);
		}

		
		
		
	}


