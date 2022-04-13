package com.shms.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


	@Entity
	@Table(name="Appointment")
	public class Appointment {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int cId;
		private String name;
		private String email;
		private Date date;
		private String time;
		private String appointmentFor;
		private String tellproblem;
		
		@ManyToOne
		@JsonIgnore
		private Patient patient;
		
		public int getcId() {
			return cId;
		}
		public void setcId(int cId) {
			this.cId = cId;
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
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getAppointmentFor() {
			return appointmentFor;
		}
		public void setAppointmentFor(String appointmentFor) {
			this.appointmentFor = appointmentFor;
		}
		public String getTellproblem() {
			return tellproblem;
		}
		public void setTellproblem(String tellproblem) {
			this.tellproblem = tellproblem;
		}
		@Override
		public String toString() {
			return "Appointment [cId=" + cId + ", name=" + name + ", email=" + email + ", date=" + date + ", time="
					+ time + ", appointmentFor=" + appointmentFor + ", tellproblem=" + tellproblem + ", patient="
					+ patient + "]";
		}
		public Patient getPatient() {
			return patient;
		}
		public void setPatient(Patient patient) {
			this.patient = patient;
		}
		
		
}
