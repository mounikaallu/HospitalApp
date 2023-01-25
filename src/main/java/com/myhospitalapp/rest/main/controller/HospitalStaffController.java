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

import com.myhospitalapp.rest.main.model.HospitalStaff;
import com.myhospitalapp.rest.main.service.HospitalStaffService;

@RestController
@RequestMapping("/api/hospitalStaff")
public class HospitalStaffController {

	@Autowired
	private HospitalStaffService hospitalStaffService;

	@PostMapping("/add")
	public ResponseEntity<String> postHospitalStaff(@RequestBody HospitalStaff hospitalStaff) {
		hospitalStaffService.insertHospitalStaff(hospitalStaff);
		return ResponseEntity.status(HttpStatus.OK).body("HospitalStaff posted in DB");
	}

	@GetMapping("/getall")
	public List<HospitalStaff> getAllHospitalStaff() {
		List<HospitalStaff> list = hospitalStaffService.getAllHospitalStaff();
		return list;
	}

	@GetMapping("/one/{id}")
	public ResponseEntity<Object> getHospitalStaffById(@PathVariable("id") int id) {
		Optional<HospitalStaff> optional = hospitalStaffService.getHospitalStaffById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");
		}
		HospitalStaff hospitalStaff = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(hospitalStaff);
	}

	
	@DeleteMapping("/delete/{id}")
	 public ResponseEntity<String> deleteHospitalStaffById(@PathVariable("id") int id){
		hospitalStaffService.deleteHospitalStaffById(id);
		 return ResponseEntity.status(HttpStatus.OK).body("Hospital Staff is deleted");
	}
	

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateHospitalStaffById(@PathVariable("id") int id,
			@RequestBody HospitalStaff hospitalStaff) {
		hospitalStaffService.updateHospitalstaffById(hospitalStaff);
		return ResponseEntity.status(HttpStatus.OK).body("hospitalstaff is updated....");

	}

}
