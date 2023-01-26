package com.myhospitalapp.rest.main.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.myhospitalapp.rest.main.model.User;
import com.myhospitalapp.rest.main.repository.UserRepository;
import com.myhospitalapp.rest.main.service.DoctorService;
import com.myhospitalapp.rest.main.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<String> postPatient(@RequestBody Patient patient) {
		patientService.insert(patient);
		return ResponseEntity.status(HttpStatus.OK).body("Patient posted.");
	}
	
	@GetMapping("/ getall")
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
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePatientById(@PathVariable("id")int id){
		patientService.deletePatientById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Patient is deleted");
		}
	
	@PostMapping("/add/{did}")
	public ResponseEntity<String> postPatient(@RequestBody Patient patient, 
							 @PathVariable("did") int did) {
		//Fetch User info from employee input and save it in DB 
		User user = patient.getUser(); //I have user name and password 
		//I will assign the role
		user.setRole("PATIENT");

		//Converting plain text password into encoded text
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		//attach encoded password to user
		user.setPassword(encodedPassword);

		user  = userRepository.save(user);


		//Attach user object to employee
		patient.setUser(user);
		//Fetch Department Object based on did.
		System.out.println("post patient");
		Optional<Doctor> doctor = doctorService.getDoctorById(did);

		//Attach department object to employee
		patient.setDoctor(doctor.get());


		//save the employee object
		patientService.postPatient(patient);
		return ResponseEntity.status(HttpStatus.OK).body("Patient posted");


	}
	@GetMapping("/bydoctorid/{did}")
	public List<Patient> getPatientByDoctorId(@PathVariable ("did") int did) {
		return patientService.getPatientByDoctorId(did);
		
	}
	
}
	
	
	
		
	

	



