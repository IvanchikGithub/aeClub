package com.aeClub.config;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.aeClub.entity.EmailPass;
import com.aeClub.enums.CountryList;
import com.aeClub.enums.DenominationType;
import com.aeClub.form.AccountForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.FindService;
import com.aeClub.service.impl.FindServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class NavigationTestsForRegistrations {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	FindService findService;

	// 4 emails and passwords (for 4 test-users) are puting in databank
	@BeforeAll
	private static void init(@Autowired CreateNewUserService createNewUserService,
			@Autowired FindService findService) {
		if (!findService.isEmailRegistred("qwe1@1.1")) {
			createNewUserService.creatingNewPairEmailAndPass("qwe1@1.1", "Zaq12wsX");
		}
		if (!findService.isEmailRegistred("qwe2@1.1")) {
			createNewUserService.creatingNewPairEmailAndPass("qwe2@1.1", "Xsw23edC");
		}
		if (!findService.isEmailRegistred("qwe3@1.1")) {
			createNewUserService.creatingNewPairEmailAndPass("qwe3@1.1", "Cde34rfV");
		}
		if (!findService.isEmailRegistred("qwe4@1.1")) {
			createNewUserService.creatingNewPairEmailAndPass("qwe4@1.1", "Vfr45tgB");
		}
//wir erstellen einen Account mit der Email "qwe1@1.1", falls dieser Account nicht existiert ist
		int idUser = 0;
		boolean fruther = true;
		Class<FindServiceImpl> classFindService = FindServiceImpl.class;
		try {
			Method method = classFindService.getDeclaredMethod("findEmailPass", String.class);
			method.setAccessible(true);
			EmailPass emailPass = (EmailPass) method.invoke(findService, "qwe1@1.1");
			idUser = emailPass.getIdUser();
		} catch (Exception e) {
			e.printStackTrace();
			fruther = false;
		}
		Assumptions.assumeTrue(fruther);
		if (idUser != 0 && findService.getAccountById(idUser) == null) {
			AccountForm accountForm = new AccountForm();
			accountForm.setNameForClub("UserWithName1");
			accountForm.setGender("man");
			accountForm.setBirthdateFromForm("1978-06-25");
			accountForm.setCountry(CountryList.UKRAINE.name());
			accountForm.setDenomination(DenominationType.NOT_SPECIFIED.name());
			accountForm.setCity("Kyiv");
			createNewUserService.createUsersMainInformation(idUser, accountForm, null, null);

		}

	}

	@Test
	@DisplayName(value = "/profile/home to /profile/registrationMainInfo for user,"
			+ "who have email and pass but haven't minimal info yet(name, birthday, gender...)")
	@WithUserDetails("qwe4@1.1")
	public void redirectUserWhoHaveEmailPassButHaveNotMainInfo() throws Exception {
		this.mockMvc.perform(get("/profile/home")).andExpect(authenticated())
				.andExpect(redirectedUrl("/profile/registrationMainInfo"));
	}

	@Test
	@DisplayName(value = "/profile/home to /profile/home for users, who have mainInfo")
	@WithUserDetails("qwe1@1.1")
	public void goToMainUserpageForUsersWhoHaveMainInfo() throws Exception {
		this.mockMvc.perform(get("/profile/home")).andExpect(authenticated()).andExpect(status().isOk())
				.andExpect(content().string(containsString("class=\"panel-title\">Main info")));
	}

	@Test
	@DisplayName(value = "/profile/registrationMainInfo POST to /profile/home with data of new users(after save) ")
	@WithUserDetails("qwe2@1.1")
	public void registrationNewAccountWithMainInfo() throws Exception {
		MockHttpServletRequestBuilder multipart = multipart("/profile/registrationMainInfo")
				.file("fileWithUsersPhoto", null).file("filesWithUsersExtraPhoto", null)
				.param("nameForClub", "UserWithName2").param("gender", "woman")
				.param("country", CountryList.AUSTRIA.name()).param("birthdateFromForm", "1991-09-11")
				.param("denomination", DenominationType.OTHER_PROTESTANT_CHURCH.getName())
				.param("city", "Salzburg").param("languagesFromForm", "[]")
				.param("hobbiesFromForm", "[]");
		this.mockMvc.perform(multipart).andExpect(authenticated())
				.andExpect(redirectedUrl("/profile/home"));
	}

	@Test
	@DisplayName(value = "POST /registration with correct email but this email is registrired to /registration again")
	public void returnToRegistrationForEmailWhoIsRegistrated() throws Exception {
		this.mockMvc
				.perform(post("/registration").param("email", "qwe1@1.1").param("password1", "Zaq12wsX")
						.param("password2", "Zaq12wsX"))
				.andExpect(content().string(containsString("label for=\"email\">Email address</label")));
	}

}
