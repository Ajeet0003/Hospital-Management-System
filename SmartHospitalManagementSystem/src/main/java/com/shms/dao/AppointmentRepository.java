package com.shms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shms.entities.Appointment;
import com.shms.entities.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	//pagination...
	
	
	@Query("from Appointment as c where c.patient.id=:patientId")
	public List<Appointment> findAppointmentsByPatient(@Param("patientId") int patientId);
	
	//search
	public List<Appointment> findByNameContainingAndPatient(String name,Patient patient );
	
}
