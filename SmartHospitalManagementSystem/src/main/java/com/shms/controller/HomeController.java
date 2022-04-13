package com.shms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shms.helper.Message;
import com.shms.dao.PatientRepository;
import com.shms.entities.Patient;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private PatientRepository patientRepository;
	
	
	@RequestMapping("/")
	public String home(Model model) {
		
		model.addAttribute("title","Home-Hospital Management Sytem");
		return "home";
	}
	
	@RequestMapping("/about")
	public String about(Model model) {
		
		model.addAttribute("title","About-Hospital Management Sytem");
		return "about";
	}
	@RequestMapping("/signup")
	public String signup(Model model) {
		
		model.addAttribute("title","Register-Hospital Management Sytem");
		model.addAttribute("patient",new Patient());
		
		return "signup";
	}
	
	//handler for registering patient
	
		@RequestMapping(value="/do_register", method =RequestMethod.POST)
		public String registerPatient(@ModelAttribute("patient") Patient patient,@RequestParam(value="agreement",defaultValue = "false") boolean agreement ,Model model,HttpSession session) {
			
			try {
				if(!agreement) {
					System.out.println("you not agreed terms n c");
					throw new Exception("you not agreed terms and conditions");
				}
				patient.setRole("ROLE_PATIENT");
				patient.setEnabled(true);
				
				//patient.setPassword(PasswordEncoder.encode(patient.getPassword()));
				patient.setPassword(passwordEncoder.encode(patient.getPassword()));
				
				System.out.println("Agreement "+agreement);
				System.out.println("Patient "+patient);
				
			Patient	result=	this.patientRepository.save(patient);
				
				model.addAttribute("patient",new Patient());
				
				session.setAttribute("message", new Message("Successfully registered !!","alert-success"));
				return "signup";
				
			} catch (Exception e) {
			
				e.printStackTrace();
				model.addAttribute("patient",patient);
				session.setAttribute("message", new Message("Something went Wrong !! "+e.getMessage(),"alert-danger"));
				return "signup";
			}

		}
		//handler for custom login
		
		@GetMapping("/signin")
		public String customLogin(Model model) {
			model.addAttribute("title", "login-Hospital Management system");
			return "login";
		}
	}

