package com.shms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shms.config.CustomUserDetails;
import com.shms.dao.PatientRepository;
import com.shms.entities.Patient;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// fetching user from database
		
		Patient patient=patientRepository.getPatientByUserName(username);
		
		if(patient==null) {
			throw new UsernameNotFoundException("Could not found patient !!");
			
		}
		
		CustomUserDetails customUserDetails=new CustomUserDetails(patient);
		return customUserDetails;
	}

}
