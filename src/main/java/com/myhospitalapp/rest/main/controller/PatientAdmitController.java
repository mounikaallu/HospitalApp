package com.myhospitalapp.rest.main.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myhospitalapp.rest.main.model.Department;
import com.myhospitalapp.rest.main.model.Doctor;
import com.myhospitalapp.rest.main.model.Patient;
import com.myhospitalapp.rest.main.model.PatientAdmit;
import com.myhospitalapp.rest.main.service.DepartmentService;
import com.myhospitalapp.rest.main.service.DoctorService;
import com.myhospitalapp.rest.main.service.PatientAdmitService;
import com.myhospitalapp.rest.main.service.PatientService;
@RestController
public class PatientAdmitController {

	@Autowired
	private PatientService patientService;
	@Autowired
	 private DepartmentService departmentService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientAdmitService patientAdmitService;
	
	@PostMapping("api/patient/department/doctor/add/{patientId}/{departmentId}/{doctorId}")
    public ResponseEntity<String> assignInstructorToCourse(@RequestBody PatientAdmit patientAdmit,
            @PathVariable("patientId") int patientId,
            @PathVariable("departmentId") int departmentId,
            @PathVariable("doctorId") int doctorId){


       
        Optional<Patient> optionalC = patientService.getPatientById(patientId);

        if(!optionalC.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Patient ID Given");
        Optional<Doctor> optionald = doctorService.getDoctorById(doctorId);
        if(!optionald.isPresent())
        {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Doctor ID Given");
        }


        
        Optional<Department> optionalI = departmentService.getDepartmentById(departmentId);

        if(!optionalI.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid department ID Given");
        /* fetch these patient and doctor objects from optional*/
        Patient patient = optionalC.get();
        Department department = optionalI.get();
        Doctor doctor=optionald.get();
        /* set these patient and doctor objects to patientDoctor object*/
        patientAdmit.setPatient(patient);
        patientAdmit.setDepartment(department);
        patientAdmit.setDoctor(doctor);

        patientAdmitService.insert(patientAdmit);
        return ResponseEntity.status(HttpStatus.OK).body("Department assigned to patient..");        



    }

	
	
}
