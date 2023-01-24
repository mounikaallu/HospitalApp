package com.myhospitalapp.rest.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		
		/* Validate patientId, fetch Patient Object */
		java.util.Optional<Patient> optionalP =  patientService.getPatientById(patientId);
	
		if(!optionalP.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Patient ID");
		
		/* Validate doctorId, fetch Doctor Object*/
		java.util.Optional<Doctor> optionalD = doctorService.getDoctorById(doctorId);
		
		if(!optionalD.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Doctor Id");
		
		//fetch patient and doctor object from optional
		Patient patient = optionalP.get();
		Doctor doctor = optionalD.get();
		
		/* set these patient and doctor objects to patientDoctor object */
		patientDoctor.setDoctor(doctor);
		patientDoctor.setPatient(patient);
		
		/* save patientDoctor object in DB */
		patientDoctorService.insert(patientDoctor);
		
		return ResponseEntity.status(HttpStatus.OK).body("Appointment confirmed");
	}
	
}
