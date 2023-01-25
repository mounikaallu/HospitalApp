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

import com.myhospitalapp.rest.main.model.Doctor;
import com.myhospitalapp.rest.main.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    
	@Autowired
	private DoctorService doctorService;

	 @PostMapping("/add")
     public ResponseEntity<String> postDoctor(@RequestBody Doctor doctor) {
	 doctorService.insertDoctor(doctor);
	 return ResponseEntity.status(HttpStatus.OK).body("Doctor Posted in DB");
	}
	@GetMapping("/getall")
	public List<Doctor> getAllDoctor() {
		List<Doctor> list =doctorService.getAllDoctor();
		return list;
	}
	@GetMapping("/one/{id}")
	public ResponseEntity<Object> getDoctorById(@PathVariable("id")int id) {
		Optional<Doctor> optional =doctorService.getDoctorById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		}
		Doctor doctor = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
		}
	@DeleteMapping("/two/{id}")
	public ResponseEntity<Object> deleteHospitalStaffById(@PathVariable("id")int id){
		Optional<Doctor> optional = doctorService.deleteDoctorById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		}
		Doctor doctor = optional.get();
	    return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
		
	}
	//@PutMapping("/three/{id}")
	
}



