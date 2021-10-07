package com.aeClub.controller;

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

import com.aeClub.form.CreateEmailPassForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.util.CurrentProfile;
import com.aeClub.util.SecurityUtil;
import com.aeClub.validator.CreateEmailPassValidator;

/**
 * HomeController deckt nächste Internetadresse:<br>
 * /home<br>
 * /login-failed<br>
 * /registration<br>
 * /registration POST<br>
 * 
 * 
 * @author ivasy
 *
 */
@Controller
public class HomeController {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CreateNewUserService createService;

	@Autowired
	private CreateEmailPassValidator createEmailPassValidator;

	/**
	 * Set a validator CreateEmailPassValidator for form CreateEmailPassForm
	 * 
	 * @see CreateEmailPassForm
	 * @see CreateEmailPassValidator
	 **/
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == CreateEmailPassForm.class) {
			dataBinder.setValidator(createEmailPassValidator);
		}
	}

	@GetMapping(value = "/home")
	public ModelAndView getHome() {

		if (SecurityUtil.isCurrentProfileAuthentificated()) {
			return new ModelAndView("redirect:profile/home");
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
	public ModelAndView createNewPairEmailPassForNewUser(Model model) {

		model.addAttribute("createEmailPassForm", new CreateEmailPassForm());
		return new ModelAndView("/registration");
	}

	@PostMapping(value = "/registration")
	public ModelAndView createNewEmailPassPost(
			@ModelAttribute("createEmailPassForm") @Validated CreateEmailPassForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/registration");
		}
		createService.createNewPairEmailAndPass(form.getEmail(), form.getPassword1());
		return new ModelAndView("redirect:/profile/registrationMainInfo");

	}

}
