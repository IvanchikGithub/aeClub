package com.aeClub.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aeClub.form.NewUserForm1;
import com.aeClub.model.CurrentProfile;
import com.aeClub.service.CreateService;
import com.aeClub.service.FindService;
import com.aeClub.util.SecurityUtil;

@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CreateService createService;

	@Autowired
	private FindService findService;

	@GetMapping(value = "/home")
	public ModelAndView getHome(@AuthenticationPrincipal CurrentProfile currentProfile) {
		if (SecurityUtil.isCurrentProfileAuthentificated()) {
			return new ModelAndView("redirect:/profile/newuser");
		} else {
			return new ModelAndView("/home");
		}
		
	}

	@GetMapping(value = "/login-failed")
	public ModelAndView getLoginFailed(HttpSession session, Model model) {
//		if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
//			return new ModelAndView("redirect: /home2");
//		}
		model.addAttribute("errorMessage", "Invalid username and password.");
		return new ModelAndView("/home");
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView formNewEmailPass(Model model) {

		model.addAttribute("emailPassForm", new NewUserForm1());
		return new ModelAndView("/registration");
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewEmailPassPost(@ModelAttribute("newUserForm1") NewUserForm1 form, Model model,
			HttpServletResponse response) {

		createService.createNewUser(form.getEmail1(), form.getPassword1());
		return new ModelAndView("redirect:/profile/newuser");

	}

	@GetMapping(value = "/profile/newuser")
	public ModelAndView formNewUser(@AuthenticationPrincipal CurrentProfile currentProfile, Model model) {
		System.out.println(currentProfile);
		return new ModelAndView("/profile/newuser");

	}


	
}
