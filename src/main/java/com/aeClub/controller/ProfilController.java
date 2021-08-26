package com.aeClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProfilController {
	
	@GetMapping (value = "/profile/home")
	public ModelAndView getProfilHome () {
		return new ModelAndView("/home2");
	}
}
