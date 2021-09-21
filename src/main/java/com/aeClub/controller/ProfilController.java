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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aeClub.entity.Account;
import com.aeClub.entity.Hobby;
import com.aeClub.entity.Language;
import com.aeClub.form.AccountForm;
import com.aeClub.model.AmmountChildrenType;
import com.aeClub.model.CountryList;
import com.aeClub.model.CurrentProfile;
import com.aeClub.model.DenominationType;
import com.aeClub.model.EducationLevel;
import com.aeClub.model.GenderType;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.EditService;
import com.aeClub.service.GetService;
import com.aeClub.util.EnumUtil;
import com.aeClub.validator.AccountFormValidator;

@Controller
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
		model.addAttribute("rootPath", createService.getRootPath());
		return new ModelAndView("/profile/home");
	}
	@GetMapping(value = "/profile/home/{wallType}")
	public ModelAndView getUsersHomeOtherWall(@AuthenticationPrincipal CurrentProfile currentProfile, Model model, @PathVariable String wallType) {
		Account account = getService.getAccountById(currentProfile.getId());
		account.setActiveWall(editService.changeActiveWall(Integer.parseInt(wallType)));
		model.addAttribute("account", account);
		
		model.addAttribute("rootPath", createService.getRootPath());
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
		List<EducationLevel> educationLevelList = EnumUtil.getEducationLevelList();
		model.addAttribute("educationLevelList", educationLevelList);
		List<AmmountChildrenType> ammountChildrenTypeList = EnumUtil.getAmmountChildrenList();
		model.addAttribute("ammountChildrenTypeList", ammountChildrenTypeList);
		List<Hobby> hobbies = EnumUtil.getHobbiesList();
		model.addAttribute("hobbies", hobbies);
		List<Language> languages = EnumUtil.getLanguagesList();
		model.addAttribute("languages", languages);
		
		
		model.addAttribute("accountForm", new AccountForm());
		return new ModelAndView("/profile/registrationMainInfo");
	}

	@PostMapping(value = "/profile/registrationMainInfo")
	public ModelAndView uploadUsersData(@AuthenticationPrincipal CurrentProfile currentProfile,
			Model model, @ModelAttribute("accountForm") @Validated AccountForm accountForm,
			BindingResult result, @RequestParam("fileWithUsersPhoto") MultipartFile fileWithUsersPhoto,
			RedirectAttributes redirectAttributes,
			@RequestParam("filesWithUsersExtraPhoto") MultipartFile[] filesWithUsersExtraPhoto) {

		if (result.hasErrors()) {
			return new ModelAndView("/profile/registrationMainInfo");
		}

		createService.createUsersMainInformation(currentProfile.getId(), accountForm, fileWithUsersPhoto,
				filesWithUsersExtraPhoto);

		return new ModelAndView("redirect:/profile/home");
	}

}
