package com.shms.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shms.entities.Patient;

public class CustomUserDetails implements UserDetails{

	private Patient patient;
	
	
	public CustomUserDetails(Patient patient) {
		super();
		this.patient = patient;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(patient.getRole());
		
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		
		return patient.getPassword();
	}

	@Override
	public String getUsername() {
	
		return patient.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
