package com.myhospitalapp.rest.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import com.myhospitalapp.rest.main.enums.Specialization;

@Entity
public class Doctor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Column(name="doctor_name")
	private String name; 
	private String contactNo;
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpeciality(Specialization specialization) {
		this.specialization = specialization;
	} 

}