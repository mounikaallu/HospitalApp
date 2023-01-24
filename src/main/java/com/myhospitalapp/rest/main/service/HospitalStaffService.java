package com.myhospitalapp.rest.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myhospitalapp.rest.main.model.HospitalStaff;
import com.myhospitalapp.rest.main.repository.HospitalStaffRepository;

@Service
public class HospitalStaffService {
	
	@Autowired
	private HospitalStaffRepository hospitalStaffRepository; 

	public void insertHospitalStaff(HospitalStaff hospitalStaff) {
		hospitalStaffRepository.save(hospitalStaff);
		
	}

	public List<HospitalStaff> getAllHospitalStaff() {
		return hospitalStaffRepository.findAll();
		
	}

	public Optional<HospitalStaff> getHospitalStaffById(int id) {
		Optional<HospitalStaff> optional=hospitalStaffRepository.findById(id);
        return optional;
		
	}

	public Optional<HospitalStaff> deleteHospitalStaffById(int id) {
		Optional<HospitalStaff> optional=hospitalStaffRepository.findById(id);
        return optional;
		
		
	}

	

	
}
