package com.myhospitalapp.rest.main.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.myhospitalapp.rest.main.enums.Specialization;

@Entity
@Table(name="department")

public class Department {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int id;
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

}
