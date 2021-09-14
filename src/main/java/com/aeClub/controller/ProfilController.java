package com.aeClub.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aeClub.form.AccountForm;
import com.aeClub.model.CountryList;
import com.aeClub.model.CurrentProfile;
import com.aeClub.model.DenominationType;
import com.aeClub.model.GenderType;
import com.aeClub.service.CreateService;
import com.aeClub.util.EnumUtil;
import com.aeClub.validator.AccountFormValidator;

@Controller
public class ProfilController {
	 private static final Logger LOG = LoggerFactory.getLogger(ProfilController.class);

	
	@Autowired
	CreateService createService;
	@Autowired
	private AccountFormValidator accountFormValidator;
	
	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}

		if (target.getClass() == AccountForm.class) {
			dataBinder.setValidator(accountFormValidator);
		}

		// ...
	}
	

	@GetMapping(value = "/profile/newuser")
	public ModelAndView formNewUser(Model model) {
		return new ModelAndView("/profile/newuser");
	}

	@GetMapping(value = "/profile/home")
	public ModelAndView getUsersHome(Model model) {
		return new ModelAndView("/profile/home");
	}

	@GetMapping(value = "/profile/registrationMainInfo")
	public ModelAndView getEditUsersMainInfo(@AuthenticationPrincipal CurrentProfile currentProfile,
			Model model) {
		
		List<GenderType> listGender = EnumUtil.getGenderTypes();
		model.addAttribute("listGender", listGender);
		List<CountryList> listCountry = EnumUtil.getCountryList();
		model.addAttribute("listCountry", listCountry);
		List<DenominationType> listDenomination = EnumUtil.getDenominationList();
		model.addAttribute("listDenomination", listDenomination);
		model.addAttribute("accountForm", new AccountForm());
		return new ModelAndView("/profile/registrationMainInfo");
	}

	@PostMapping(value = "/profile/registrationMainInfo")
	public ModelAndView uploadUsersData(@AuthenticationPrincipal CurrentProfile currentProfile,
			Model model, @ModelAttribute("accountForm") @Validated AccountForm accountForm,
			BindingResult result, @RequestParam("file") MultipartFile fileWithUsersPhoto,
			RedirectAttributes redirectAttributes) {
		
//		if (result.hasErrors()) {
//			return new ModelAndView("/profile/registrationMainInfo");
//		}

		createService.savePhoto(fileWithUsersPhoto, "");
		
		//createService.createUsersMainInformation(currentProfile.getId(), accountForm, fileWithUsersPhoto);
		
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + fileWithUsersPhoto.getOriginalFilename() + "!");
		return new ModelAndView("redirect:/profile/newuser");
	}
	

	
}
