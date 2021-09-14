package com.aeClub.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aeClub.form.CreateEmailPassForm;
import com.aeClub.model.CurrentProfile;
import com.aeClub.service.CreateService;
import com.aeClub.util.SecurityUtil;
import com.aeClub.validator.CreateEmailPassValidator;

@Controller
public class HomeController {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CreateService createService;

	@Autowired
	private CreateEmailPassValidator createEmailPassValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}

		if (target.getClass() == CreateEmailPassForm.class) {
			dataBinder.setValidator(createEmailPassValidator);
		}

		// ...
	}

	@GetMapping(value = "/home")
	public ModelAndView getHome(@AuthenticationPrincipal CurrentProfile currentProfile, Model model) {

		if (SecurityUtil.isCurrentProfileAuthentificated()) {
			return new ModelAndView("redirect:profile/registrationMainInfo");
		} else {
			return new ModelAndView("/home");
		}

	}

	@GetMapping(value = "/login-failed")
	public ModelAndView getLoginFailed(Model model) {
		model.addAttribute("errorMessage", "Data are not correct");
		return new ModelAndView("/home");
	}

	@GetMapping(value = "/registration")
	public ModelAndView formNewEmailPass(Model model) {

		model.addAttribute("createEmailPassForm", new CreateEmailPassForm());
		return new ModelAndView("/registration");
	}

	@PostMapping(value = "/registration")
	public ModelAndView createNewEmailPassPost(
			@ModelAttribute("createEmailPassForm") @Validated CreateEmailPassForm form, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/registration");
		}
		createService.createNewPaarEmailPassAndIdUser(form.getEmail1(), form.getPassword1());
		return new ModelAndView("redirect:/profile/registrationMainInfo");

	}

}
