package com.myhospitalapp.rest.main.controller;


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

	@GetMapping("/getall")
	public List<Patient> getAllPatients() {
		List<Patient> list = patientService.getAllPatients();
		return list;
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<Object> getPatientById(@PathVariable("id") int id) {
		java.util.Optional<Patient> optional = patientService.getPatientById(id);

		if (!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Patient Id");

		Patient patient = optional.get();

		return ResponseEntity.status(HttpStatus.OK).body(patient);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePatientById(@PathVariable("id") int id) {
		patientService.deletePatientById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Patient is deleted");
	}

	@PutMapping("edit/{pid}")
	public ResponseEntity<String> editPatient(@PathVariable("pid") int pid, @RequestBody Patient patientNew) {
		Optional<Patient> optional = patientService.getPatientById(pid);
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid ID");
		}
		Patient patientDB = optional.get(); // User given employee value         /* Step 2: Set New value to DB value */
		if (patientNew.getName() != null)
			patientDB.setName(patientNew.getName());
		if (patientNew.getCity() != null)
			patientDB.setCity(patientNew.getCity());
		if (patientNew.getAge() != 0)
			patientDB.setAge(patientNew.getAge());
		patientService.postPatient(patientDB);
		return ResponseEntity.status(HttpStatus.OK).body("patient record Edited");

	}

	@PostMapping("/add/{did}")
	public ResponseEntity<String> postPatient(@RequestBody Patient patient, @PathVariable("did") int did) {
		// Fetch User info from patient input and save it in DB
		User user = patient.getUser(); // I have user name and password
		// I will assign the role
		user.setRole("PATIENT");

		// Converting plain text password into encoded text
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		// attach encoded password to user
		user.setPassword(encodedPassword);

		user = userRepository.save(user);

		// Attach user object to patient
		patient.setUser(user);
		// Fetch patient Object based on doctor id.
		System.out.println("post patient");
		Optional<Doctor> doctor = doctorService.getDoctorById(did);

		// Attach doctor object to patient
		patient.setDoctor(doctor.get());

		// save the patient object
		patientService.postPatient(patient);
		return ResponseEntity.status(HttpStatus.OK).body("Patient posted");

	}

	@GetMapping("/bydoctorid/{did}")
	public List<Patient> getPatientByDoctorId(@PathVariable("did") int did) {
		return patientService.getPatientByDoctorId(did);

	}

}
