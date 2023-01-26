package com.myhospitalapp.rest.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhospitalapp.rest.main.model.Doctor;

import com.myhospitalapp.rest.main.repository.DoctorRepository;


@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository; 

	public void insertDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
		
	}

	public List<Doctor> getAllDoctor() {
		return doctorRepository.findAll();
		
	}

	public Optional<Doctor> getDoctorById(int id) {
		Optional<Doctor> optional=doctorRepository.findById(id);
        return optional;
		
	}

	public void deleteDoctorById(int id) {
		doctorRepository.deleteById(id);
		
		
	}

	

	public void postDoctor(Doctor doctor) {
		doctorRepository.save(doctor);
		
	}

	public List<Doctor> getDoctorByDepartmentId(int depId) {
		return doctorRepository.getByDepartmentId(depId);
		
	}

}

