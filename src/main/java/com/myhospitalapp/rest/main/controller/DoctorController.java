package com.myhospitalapp.rest.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myhospitalapp.rest.main.dto.Message;
import com.myhospitalapp.rest.main.model.Department;
import com.myhospitalapp.rest.main.model.Doctor;
import com.myhospitalapp.rest.main.model.User;
import com.myhospitalapp.rest.main.repository.UserRepository;
import com.myhospitalapp.rest.main.service.DepartmentService;
import com.myhospitalapp.rest.main.service.DoctorService;
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/add")
	public ResponseEntity<Message> postDoctor(@RequestBody Doctor doctor) {
		Message m = new Message();
		try {
			doctorService.postDoctor(doctor);
			m.setMsg("Doctor added");
			return ResponseEntity.status(HttpStatus.OK).body(m);
		}
		catch(Exception e) {
			m.setMsg("Could not process the request, Try Again");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
		}
			
		}

	@GetMapping("/getall")
	public List<Doctor> getAllDoctor() {
		List<Doctor> list = doctorService.getAllDoctor();
		return list;
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<Object> getDoctorById(@PathVariable("id") int id) {
		Optional<Doctor> optional = doctorService.getDoctorById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		}
		Doctor doctor = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDoctorById(@PathVariable("id") int id) {
		doctorService.deleteDoctorById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Doctor is deleted");

	}

	@PutMapping("/edit/{did}")
	public ResponseEntity<String> editDoctor(@PathVariable("did") int did, @RequestBody Doctor doctorNew) {

		Optional<Doctor> optional = doctorService.getDoctorById(did);
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid ID");
		}
		Doctor doctorDB = optional.get(); // User given employee value  

		if (doctorNew.getName() != null)
			doctorDB.setName(doctorNew.getName());
		if (doctorNew.getContactNo() != null)
			doctorDB.setContactNo(doctorNew.getContactNo());
		if (doctorNew.getSpecialization() != null)
			doctorDB.setSpecialization(doctorNew.getSpecialization());
		doctorService.postDoctor(doctorDB);
		return ResponseEntity.status(HttpStatus.OK).body("Doctor Record Edited");
	}

	@PostMapping("/add/{depid}")
	public ResponseEntity<String> postDoctor(@RequestBody Doctor doctor, @PathVariable("depid") int depid) {

		// Fetch User info from doctor input and save it in DB
		User user = doctor.getUser(); // I have user name and password
		// I will assign the role
		user.setRole("DOCTOR");

		// Converting plain text password into encoded text
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		// attach encoded password to user
		user.setPassword(encodedPassword);

		user = userRepository.save(user);

		// Attach user object to doctor
		doctor.setUser(user);
		// Fetch Department Object based on depid.
		System.out.println("post doctor");
		Optional<Department> department = departmentService.getDepartmentById(depid);

		// Attach department object to doctor
		doctor.setDepartment(department.get());

		// save the doctor object
		doctorService.postDoctor(doctor);
		return ResponseEntity.status(HttpStatus.OK).body("doctor Appointed");

	}

	@GetMapping("/bydepartmentid/{did}")
	public List<Doctor> getDoctorByDepartmentId(@PathVariable("did") String depid) {
		return doctorService.getDoctorByDepartmentId(Integer.parseInt(depid));

	}

}
