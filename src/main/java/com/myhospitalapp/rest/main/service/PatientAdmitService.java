package com.myhospitalapp.rest.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.myhospitalapp.rest.main.model.PatientAdmit;
import com.myhospitalapp.rest.main.repository.PatientAdmitRepository;
@Service
public class PatientAdmitService {

	@Autowired
    private PatientAdmitRepository patientAdmitRepository;

    public void insert(PatientAdmit patientAdmit) {
    	patientAdmitRepository.save(patientAdmit);
    }
}
