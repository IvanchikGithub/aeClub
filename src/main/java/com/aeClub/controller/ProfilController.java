package com.aeClub.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aeClub.model.CurrentProfile;


@Controller
public class ProfilController {
	
	@GetMapping(value = "/profile/home")
	public ModelAndView getUsersHome(@AuthenticationPrincipal CurrentProfile currentProfile, Model model) {
		System.out.println(currentProfile);
		return new ModelAndView("/profile/newuser");

	}
}
