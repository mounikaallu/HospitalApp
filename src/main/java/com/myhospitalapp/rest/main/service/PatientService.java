package com.myhospitalapp.rest.main.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootapp.rest.restapp.model.Employee;
import com.myhospitalapp.rest.main.model.HospitalStaff;
import com.myhospitalapp.rest.main.model.Patient;
import com.myhospitalapp.rest.main.repository.PatientRepository;

@Service

public class PatientService {
		@Autowired
		private PatientRepository patientRepository;

		public void insert(Patient patient) {
			patientRepository.save(patient);	
		}

		public List<Patient> getAllPatients() {
			return patientRepository.findAll();
		}

		public Optional<Patient> getPatientById(int id) {
			Optional<Patient> optional=patientRepository.findById(id);
	        return optional;
		}

		public Optional<Patient> getdeletePatientById(int id) {
			Optional<Patient> optional=patientRepository.findById(id);
	        return optional;
			
		}
		// method for get patient by department specialization

//		public List<Patient> getPatientByDoctorSpecilization(int did) {
//			// TODO Auto-generated method stub
//			List<Patient> list = patientRepository.findAll();
//			List<Patient> filteredList=list.stream().filter(e->e.get().getId()==did)
//			             .collect(Collectors.toList());
//			return filteredList;
//			
//		}

		
	}


