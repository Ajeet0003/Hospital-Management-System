package com.shms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.data.repository.query.Param;

import com.shms.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer>{
	
	@Query("select u from Patient u where u.email=:email")
	public Patient getPatientByUserName(@Param("email") String email);
	
}
