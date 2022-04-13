package com.shms.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shms.helper.Message;
import com.shms.dao.AppointmentRepository;
import com.shms.entities.Appointment;
import com.shms.dao.PatientRepository;
import com.shms.entities.Patient;


@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	
	//method for adding common data to response
	@ModelAttribute
	public void addCommonData(Model model,Principal principal) {
		String patientName=principal.getName();
		//System.out.println("patient name"+patientName);
		
		Patient patient=patientRepository.getPatientByUserName(patientName);
		//System.out.println(patient);
		
		model.addAttribute("patient",patient);
		
	}
	
	
	//Dashboard home
	@RequestMapping("/index")
	public String dashboard(Model model,Principal principal) {
		
		model.addAttribute("title","patient Dashboard");
		return "/normal/patient_dashboard";
	}
	
	// open add form handler
	
	@GetMapping("/book-appointment")
	public String openBookAppointment(Model model) {
		
		model.addAttribute("title","book appointment");
		model.addAttribute("appointment",new Appointment());
		return "/normal/book_appointment_form";
	}
	
	//processing book appointment form
		@PostMapping("/process-appointment")
		public String processAppointment(@ModelAttribute Appointment appointment 
				,Principal principal, HttpSession session) {
			try {
				
				String name= principal.getName(); 
				Patient patient=this.patientRepository.getPatientByUserName(name);
				
				/*
				 * if(3>2) { throw new Exception(); }
				 */
				
				
				appointment.setPatient(patient);
				patient.getAppointments().add(appointment);
				
				this.patientRepository.save(patient);
				
//				System.out.println("DATA"+appointment);
//				
//				System.out.println("Added to data base");
				
				//mmessage success
				 session.setAttribute("message", new Message("Your appointment was added !!","success"));
				
			} catch (Exception e) {
				//System.out.println("ERROR"+e.getMessage());
				e.printStackTrace();
				 session.setAttribute("message", new Message("Something went wrong !! Try again..","danger"));

				//error message
				
				
			}
			
			return "/normal/book_appointment_form";
			
		}
	
	//show appointment handler
	@GetMapping("show-appointments")
	public String showAppointment(Model model,Principal principal) {
		model.addAttribute("title","Show patient appointments");
		
		//appointment ki list ko bhejni hai
		
				String patientName= principal.getName();
				Patient patient= this.patientRepository.getPatientByUserName(patientName); 
//				 *List<Appointment> appointmentList=patient.getAppointment();
//				 */
				
			List<Appointment> appointments=	this.appointmentRepository.findAppointmentsByPatient(patient.getPatientId());
			
			model.addAttribute("appointments",appointments);
			
		
		return "normal/show_appointment";
	}
	
	

	//handler for open update appointment

	@PostMapping("/update-appointment/{cid}")
	public String updateForm(Model model, @PathVariable("cid") Integer cid) {
		
		model.addAttribute("title","update appointment");
		
		Appointment appointment=this.appointmentRepository.findById(cid).get();
		
		model.addAttribute("appointment",appointment);
		
		
		return "normal/update_form";
	}
	
	//update appointment handler with database
	@RequestMapping(value="/process-update",method = RequestMethod.POST)
	public String updateHandler(@ModelAttribute Appointment appointment,
			Model model,HttpSession session,Principal principal) {
		
		try {
			
			Patient patient=this.patientRepository.getPatientByUserName(principal.getName());
			appointment.setPatient(patient);
			this.appointmentRepository.save(appointment);
			
			session.setAttribute("message", new Message("Your Appointment is updated...","success"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		System.out.println("appointment name"+appointment.getName());
//		System.out.println("appointment id"+appointment.getcId());
//		
		return "redirect:/patient/show-appointments";
	}
	
	
	@GetMapping("/delete/{cid}")
	public String deleteAppointment(@PathVariable("cid") Integer cId
			,Model model,HttpSession session, Principal principal) {
		
		
		Appointment appointment=this.appointmentRepository.findById(cId).get();
		
	appointment.setPatient(null);
		
	this.appointmentRepository.delete(appointment);
	session.setAttribute("message",new Message("Appointment deleted successfully...","success"));
	
	return "redirect:/patient/show-appointments";		
		
	}
	
	//your profile handler
	@GetMapping("/profile")
	public String yourprofile(Model medel) {
		
		medel.addAttribute("title","Profile Page");
		
		
		return "normal/profile";
	}
	
	//open settings handler
	@GetMapping("/settings")
	public String openSetting() {
		return "normal/settings";
	}
	
	//change password handler
	@PostMapping("/change-password")
	public String changrPassword( @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,Principal principal,HttpSession session) {
		
		String patientName= principal.getName();
		Patient currentPatient=this.patientRepository.getPatientByUserName(patientName);
		
		if(this.bCryptPasswordEncoder.matches(oldPassword,currentPatient.getPassword())) {
			currentPatient.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			this.patientRepository.save(currentPatient);
			session.setAttribute("message",new Message("Your password is successfully changed...","success"));
		}else {
			session.setAttribute("message",new Message("Please enter your correct old password...","danger"));
			return "redirect:/patient/settings";
		}
		
		
		return "redirect:/patient/index";
	}
	
	//about page
	@GetMapping("/about")
	public String aboutPage() {
		
		return "about";
	}
	
	
//	//search handler
//		@GetMapping("/search/{query}")
//		public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal){
//			
//			
//			Patient patient=this.patientRepository.getPatientByUserName(principal.getName());
//			
//		 List<Appointment> appointments=	this.appointmentRepository.findByNameContainingAndPatient(query, patient);
//		
//		return ResponseEntity.ok(appointments);
//		}
//		
	
}

