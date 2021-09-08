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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aeClub.form.EmailPassForm;
import com.aeClub.model.CurrentProfile;
import com.aeClub.util.SecurityUtil;
import com.aeClub.validator.EmailPassValidator;

@Controller
public class HomeController {
	//private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	private EmailPassValidator emailPassValidator;
	

	// Set a form validator
	   @InitBinder
	   protected void initBinder(WebDataBinder dataBinder) {
	      // Form target
	      Object target = dataBinder.getTarget();
	      if (target == null) {
	         return;
	      }
	 
	      if (target.getClass() == EmailPassForm.class) {
	         dataBinder.setValidator(emailPassValidator);
	      }
	      // ...
	   }
	
	
	
	
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

		model.addAttribute("emailPassForm", new EmailPassForm());
		return new ModelAndView("/registration");
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewEmailPassPost(@ModelAttribute("emailPassForm") @Validated EmailPassForm form, BindingResult result, 
			final RedirectAttributes redirectAttributes) {
		
		 if (result.hasErrors()) {
	         return  new ModelAndView("/registration");
	      }
		
		return new ModelAndView("redirect:/profile/newuser");

	}

	@GetMapping(value = "/profile/newuser")
	public ModelAndView formNewUser(@AuthenticationPrincipal CurrentProfile currentProfile, Model model) {
		System.out.println(currentProfile);
		return new ModelAndView("/profile/newuser");

	}


	
}
