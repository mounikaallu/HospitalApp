package com.myhospitalapp.rest.main.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class PatientAdmit {

	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int bedNo;
	
	public int getBedNo() {
		return bedNo;
	}
	public void setBedNo(int bedNo) {
		this.bedNo = bedNo;
	}
	@OneToOne
    private Patient patient;
    @ManyToOne
    private Department department;
    @OneToOne
    private Doctor doctor;
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
    
}

