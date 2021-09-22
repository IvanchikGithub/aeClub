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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aeClub.entity.Account;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.enums.AmmountChildrenType;
import com.aeClub.enums.CountryList;
import com.aeClub.enums.DenominationType;
import com.aeClub.enums.EducationLevel;
import com.aeClub.enums.GenderType;
import com.aeClub.form.AccountForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.EditService;
import com.aeClub.service.GetService;
import com.aeClub.util.CurrentProfile;
import com.aeClub.util.EnumUtil;
import com.aeClub.validator.AccountFormValidator;

@Controller
@SessionAttributes("account")
public class ProfilController {
	private static final Logger LOG = LoggerFactory.getLogger(ProfilController.class);

	@Autowired
	private CreateNewUserService createService;
	@Autowired
	private GetService getService;
	@Autowired
	private EditService editService;

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

	@GetMapping(value = "/profile/home")
	public ModelAndView getUsersHome(@AuthenticationPrincipal CurrentProfile currentProfile, Model model) {
		Account account = getService.getAccountById(currentProfile.getId());
		if (account.getClass().getSimpleName().equals("AccountEmpty")) {
			return new ModelAndView("redirect:/profile/registrationMainInfo");
		}
		model.addAttribute("account", account);
		return new ModelAndView("/profile/home");
	}
	@GetMapping(value = "/profile/home/{wallType}")
	public ModelAndView getUsersHomeOtherWall(Model model, @ModelAttribute ("account") Account account, @PathVariable String wallType) {
		account.setActiveWall(editService.changeActiveWall(Integer.parseInt(wallType)));
		model.addAttribute("account", account);
		return new ModelAndView("/profile/home");
	}

	@GetMapping(value = "/profile/registrationMainInfo")
	public ModelAndView getEditUsersMainInfo(Model model) {

		model = getService.getDataFromCatalogues (model);
		
		model.addAttribute("accountForm", new AccountForm());
		return new ModelAndView("/profile/registrationMainInfo");
	}

	@PostMapping(value = "/profile/registrationMainInfo")
	public ModelAndView uploadUsersData(@AuthenticationPrincipal CurrentProfile currentProfile,
			@ModelAttribute("accountForm") @Validated AccountForm accountForm,
			BindingResult result, @RequestParam("fileWithUsersPhoto") MultipartFile fileWithUsersPhoto,
			//RedirectAttributes redirectAttributes,
			@RequestParam("filesWithUsersExtraPhoto") MultipartFile[] filesWithUsersExtraPhoto) {

		if (result.hasErrors()) {
			return new ModelAndView("/profile/registrationMainInfo");
		}

		createService.createUsersMainInformation(currentProfile.getId(), accountForm, fileWithUsersPhoto,
				filesWithUsersExtraPhoto);

		return new ModelAndView("redirect:/profile/home");
	}
	
	@GetMapping (value="/profile/settings")
	public ModelAndView profileSettings (Model model) {
		model = getService.getDataFromCatalogues (model);
		model.addAttribute("accountForm", new AccountForm());
		return new ModelAndView("/profile/settings");
	}
	
	@GetMapping (value="/profile/settings/{settingsWallType}")
	public ModelAndView getSettingsOtherWall(Model model, @ModelAttribute ("account") Account account, @PathVariable String settingsWallType) {
		account.setActiveSettingsWall(editService.changeActiveSettingsWall(Integer.parseInt(settingsWallType)));
		model.addAttribute("account", account);
		model = getService.getDataFromCatalogues (model);
		return new ModelAndView("/profile/settings");
	}

}
