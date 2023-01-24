package com.myhospitalapp.rest.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhospitalapp.rest.main.model.HospitalStaff;
import com.myhospitalapp.rest.main.model.Patient;
import com.myhospitalapp.rest.main.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@PostMapping("/add")
	public ResponseEntity<String> postPatient(@RequestBody Patient patient) {
		patientService.insert(patient);
		return ResponseEntity.status(HttpStatus.OK).body("Patient posted.");
	}
	
	@GetMapping("/all")
	public List<Patient> getAllPatients() {
		List<Patient> list = patientService.getAllPatients();
		return list;
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<Object> getPatientById(@PathVariable("id") int id) {
		java.util.Optional<Patient> optional = patientService.getPatientById(id);
		
		if(!optional.isPresent()) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Patient Id");
		 
		Patient patient = optional.get();
		
		return ResponseEntity.status(HttpStatus.OK).body(patient);
	}
	
	@DeleteMapping("/two/{id}")
	public ResponseEntity<Object> deletePatientById(@PathVariable("id")int id){
		Optional<Patient> optional = patientService.getdeletePatientById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		}
		Patient patient = optional.get();
	    return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
		
	}
	//@PutMapping("/three/{id}")
	

}


