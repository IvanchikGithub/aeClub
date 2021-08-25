package com.aeClub.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping (value = "/home")
	public ModelAndView getHome () {
		return new ModelAndView("home");
	}

	
	
	@GetMapping (value = "/login-failed")
	public ModelAndView getLoginFailed (HttpSession session) {
		if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")==null) {
			return new ModelAndView ("redirect: /home");
		}
		return new ModelAndView("/home");
	}
	
}
