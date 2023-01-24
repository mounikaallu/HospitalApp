package com.myhospitalapp.rest.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myhospitalapp.rest.main.model.HospitalStaff;

public interface HospitalStaffRepository extends JpaRepository<HospitalStaff, Integer> {

}
