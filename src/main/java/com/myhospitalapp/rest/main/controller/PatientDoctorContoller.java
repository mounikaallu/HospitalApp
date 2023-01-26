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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhospitalapp.rest.main.model.Doctor;
import com.myhospitalapp.rest.main.model.Patient;
import com.myhospitalapp.rest.main.model.PatientDoctor;
import com.myhospitalapp.rest.main.service.DoctorService;
import com.myhospitalapp.rest.main.service.PatientDoctorService;
import com.myhospitalapp.rest.main.service.PatientService;

@RestController
@RequestMapping("/api/patient/doctor")
public class PatientDoctorContoller {
	@Autowired
	private PatientService patientService; 
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientDoctorService patientDoctorService;
	
	@PostMapping("/appointment/{patientId}/{doctorId}")
	public ResponseEntity<String> bookAppointment(@PathVariable("patientId") int patientId, 
								@PathVariable("doctorId") int doctorId, 
								@RequestBody PatientDoctor patientDoctor) {
		
		
		Optional<Patient> optionalP =  patientService.getPatientById(patientId);
	
		if(!optionalP.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Patient ID");
		
		
		java.util.Optional<Doctor> optionalD = doctorService.getDoctorById(doctorId);
		
		if(!optionalD.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Doctor Id");
		
	
		Patient patient = optionalP.get();
		Doctor doctor = optionalD.get();
		
	
		patientDoctor.setDoctor(doctor);
		patientDoctor.setPatient(patient);
		
		
		patientDoctorService.insert(patientDoctor);
		
		return ResponseEntity.status(HttpStatus.OK).body("Appointment confirmed");
	}
	
	@GetMapping("/getall")
	public List<PatientDoctor> getAllPatientDoctor() {
		List<PatientDoctor> list =patientDoctorService.getAllPatientDoctor();
		return list;
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<Object> getPatientDoctorById(@PathVariable("id")int id) {
		Optional<PatientDoctor> optional =patientDoctorService.getPatientDoctorById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		}
		PatientDoctor patientDoctor = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(patientDoctor);
		}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePatientDoctorById(@PathVariable("id") int id){
	    patientDoctorService.deletePatientDoctorById(id);
	    
	    return ResponseEntity.status(HttpStatus.OK).body("PatientDoctor is deleted");
  }
	
}
