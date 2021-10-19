package com.aeClub.controller;

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

import com.aeClub.entity.Account;
import com.aeClub.enums.SettingsWallType;
import com.aeClub.form.AccountForm;
import com.aeClub.form.ChangePassForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.EditService;
import com.aeClub.service.FindService;
import com.aeClub.service.GetService;
import com.aeClub.util.CurrentProfile;
import com.aeClub.validator.AccountFormValidator;
import com.aeClub.validator.ChangePassValidator;

/**
 * ProfilController deckt nächste Internetadresse:<br>
 * /profile/home<br>
 * /profile/home/{wallType}<br>
 * /profile/registrationMainInfo GET und POST<br>
 * /profile/settings/pictures GET und POST<br>
 * /profile/settings/password GET und POST<br>
 * /profile/settings/other GET und POST<br>
 * 
 * @author ivasy
 *
 */
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
	private FindService findService;

	@Autowired
	private AccountFormValidator accountFormValidator;
	@Autowired
	private ChangePassValidator changePassValidator;

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
		if (target.getClass() == ChangePassForm.class) {
			dataBinder.setValidator(changePassValidator);
		}

		// ...
	}

	@GetMapping(value = "/profile/home")
	public ModelAndView getUsersHome(@AuthenticationPrincipal CurrentProfile currentProfile,
			Model model) {
		Account account = findService.getAccountById(currentProfile.getId());

		if (account.getClass().getSimpleName().equals("AccountEmpty")) {
			// Der Nutzer hat seine Information noch nicht eingegeben, deswegen muss er auf die Seite
			// gehen, wo er diese Information eingeben kann.
			return new ModelAndView("redirect:/profile/registrationMainInfo");
		}
		model.addAttribute("account", account);
		return new ModelAndView("/profile/home");
	}

	@GetMapping(value = "/profile/home/{wallType}")
	public ModelAndView getUsersHomeOtherWall(Model model, @ModelAttribute("account") Account account,
			@PathVariable String wallType) {
		account.setActiveWall(editService.changeActiveWall(wallType));
		model.addAttribute("account", account);
		return new ModelAndView("/profile/home");
	}

	@GetMapping(value = "/profile/registrationMainInfo")
	public ModelAndView getEditUsersMainInfo(Model model) {

		model = getService.getDataFromCatalogues(model);

		model.addAttribute("accountForm", new AccountForm());
		return new ModelAndView("/profile/registrationMainInfo");
	}

	@PostMapping(value = "/profile/registrationMainInfo")
	public ModelAndView uploadUsersData(Model model,
			@AuthenticationPrincipal CurrentProfile currentProfile,
			@ModelAttribute("accountForm") @Validated AccountForm accountForm, BindingResult result,
			@RequestParam("fileWithUsersPhoto") MultipartFile fileWithUsersPhoto,
			@RequestParam("filesWithUsersExtraPhoto") MultipartFile[] filesWithUsersExtraPhoto) {

		if (result.hasErrors()) {
			model = getService.getDataFromCatalogues(model);
			return new ModelAndView("/profile/registrationMainInfo");
		}

		createService.createUsersMainInformation(currentProfile.getId(), accountForm, fileWithUsersPhoto,
				filesWithUsersExtraPhoto);

		return new ModelAndView("redirect:/profile/home");
	}

	@GetMapping(value = "/profile/settings/mainInfo")
	public ModelAndView getSettingsWallMainInfo(Model model,
			@AuthenticationPrincipal CurrentProfile currentProfile) {
		Account account = findService.getAccountById(currentProfile.getId());
		if (account.getClass().getSimpleName().equals("AccountEmpty")) {
			return new ModelAndView("redirect:/profile/home");
		}
		account.setActiveSettingsWall(SettingsWallType.MAIN_INFO);
		model.addAttribute("account", account);
		model = getService.getDataFromCatalogues(model);
		model = editService.setCheckedInHobbiesAndLanguagesLists(model, account);
		model.addAttribute("accountForm", new AccountForm());
		return new ModelAndView("/profile/settings");
	}

	@PostMapping(value = "/profile/settings/mainInfo")
	public ModelAndView postSettingsMainInfo(
			@ModelAttribute("accountForm") @Validated AccountForm accountForm, BindingResult result,
			Model model, @ModelAttribute("account") Account account) {
		if (result.hasErrors()) {
			// Die Behandlung die Fehler muss man noch schreiben
			return new ModelAndView("/profile/settings");
		}
		editService.editAccount(account, accountForm);
		return new ModelAndView("redirect:/profile/settings/mainInfo");
	}

	@GetMapping(value = "/profile/settings/pictures")
	public ModelAndView getSettingsWallPictures(Model model,
			@ModelAttribute("account") Account account) {
		account.setActiveSettingsWall(SettingsWallType.PICTURES);
		account.getPictures();
		model.addAttribute("accountForm", new AccountForm());
		return new ModelAndView("/profile/settings");
	}

	@PostMapping(value = "/profile/settings/pictures")
	public ModelAndView postSettingPicture(Model model, @ModelAttribute("account") Account account,
			@RequestParam("fileWithUsersPhoto") MultipartFile fileWithUsersPhoto,
			@RequestParam("filesWithUsersExtraPhoto") MultipartFile[] filesWithUsersExtraPhoto) {
		if (fileWithUsersPhoto != null || filesWithUsersExtraPhoto != null) {
			editService.editAccountsPictures(account, fileWithUsersPhoto, filesWithUsersExtraPhoto);
		}
		account.setActiveSettingsWall(SettingsWallType.PICTURES);
		model.addAttribute("account", account);
		return new ModelAndView("/profile/settings");
	}

	@GetMapping(value = "/profile/settings/password")
	public ModelAndView getSettingsWallPassword(Model model,
			@ModelAttribute("account") Account account) {
		account.setActiveSettingsWall(SettingsWallType.PASSWORD);
		model.addAttribute("changePassForm", new ChangePassForm());
		return new ModelAndView("/profile/settings");
	}

	@PostMapping(value = "/profile/settings/password")
	public ModelAndView postChangePassword(Model model,
			@ModelAttribute("changePassForm") @Validated ChangePassForm form, BindingResult result,
			@ModelAttribute("account") Account account) {
		if (result.hasErrors()) {
			return new ModelAndView("/profile/settings");
		}
		if (!findService.isPasswordCorrect(form.getOldPassword(), account.getIdUser())) {
			model.addAttribute("wrongPassword", "Old password is wrong");
			return new ModelAndView("/profile/settings");
		}
		editService.editPass(form.getPassword1(), account.getIdUser());
		account.setActiveSettingsWall(SettingsWallType.PASSWORD);
		model.addAttribute("success", "Password changed successfully");
		return new ModelAndView("/profile/settings");
	}

	@GetMapping(value = "/profile/settings/other")
	public ModelAndView getSettingsWallOther(Model model, @ModelAttribute("account") Account account) {
		account.setActiveSettingsWall(SettingsWallType.OTHER);
		return new ModelAndView("/profile/settings");
	}

}
