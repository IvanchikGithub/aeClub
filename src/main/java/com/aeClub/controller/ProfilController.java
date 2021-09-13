package com.aeClub.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aeClub.form.AccountForm;
import com.aeClub.model.CurrentProfile;
import com.aeClub.model.GenderType;
import com.aeClub.util.EnumUtil;


@Controller
public class ProfilController {
	
	
	@GetMapping(value = "/profile/newuser")
	public ModelAndView formNewUser(Model model) {
		return new ModelAndView("/profile/newuser");
	}
	
	@GetMapping(value = "/profile/home")
	public ModelAndView getUsersHome(Model model) {
		return new ModelAndView("/profile/home");
	}

	@GetMapping(value = "/profile/registrationMainInfo")
	public ModelAndView getEditUsersMainInfo(@AuthenticationPrincipal CurrentProfile currentProfile, Model model) {
		List<GenderType> listGender = EnumUtil.getGenderTypes();
		model.addAttribute("listGender", listGender);
		model.addAttribute("accountForm", new AccountForm());		
		return new ModelAndView("/profile/registrationMainInfo");
	}
	
}
