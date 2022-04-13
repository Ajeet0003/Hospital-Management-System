package com.shms.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


	@Entity
	@Table(name="patient")
	public class Patient {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int patientId;
		private String name;
		@Column(unique=true)
		private String email;
		private String password;
		private long contactNo;
		private int age;
		private String gender;
		private String bloodGroup;
		private String address;
		private String anyMajorDiseaseEarlier;
		private String role;
		private boolean enabled;
		
		@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "patient")
		private List<Appointment> appointments=new ArrayList<>();
		
		public int getPatientId() {
			return patientId;
		}
		public void setPatientId(int patientId) {
			this.patientId = patientId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public long getContactNo() {
			return contactNo;
		}
		public void setContactNo(long contactNo) {
			this.contactNo = contactNo;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getBloodGroup() {
			return bloodGroup;
		}
		public void setBloodGroup(String bloodGroup) {
			this.bloodGroup = bloodGroup;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getAnyMajorDiseaseEarlier() {
			return anyMajorDiseaseEarlier;
		}
		public void setAnyMajorDiseaseEarlier(String anyMajorDiseaseEarlier) {
			this.anyMajorDiseaseEarlier = anyMajorDiseaseEarlier;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		@Override
		public String toString() {
			return "Patient [patientId=" + patientId + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", contactNo=" + contactNo + ", age=" + age + ", gender=" + gender + ", bloodGroup=" + bloodGroup
					+ ", address=" + address + ", anyMajorDiseaseEarlier=" + anyMajorDiseaseEarlier + ", role=" + role
					+ ", enabled=" + enabled + ", appointments=" + appointments + "]";
		}
		public Patient() {
			super();
			// TODO Auto-generated constructor stub
		}
		public List<Appointment> getAppointments() {
			return appointments;
		}
		public void setAppointments(List<Appointment> appointments) {
			this.appointments = appointments;
		}
		
		
}
