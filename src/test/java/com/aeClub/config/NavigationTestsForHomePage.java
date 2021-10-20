package com.aeClub.config;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.FindService;

/**
 * In dieser Klasse ueberpruefen wir nur richtige Navigation<br>
 * Man muss eine richtige Seite erhalten.<br>
 * 
 * @author ivasy
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class NavigationTestsForHomePage {

	@Autowired
	private MockMvc mockMvc;

	// 4 emails and passwords (for 4 test-users) are puting in databank
	@BeforeAll
	private static void init(@Autowired	CreateNewUserService createNewUserService,
			@Autowired FindService findService) {
		if (!findService.isEmailRegistred("qwe1@1.1")) {
			createNewUserService.creatingNewPairEmailAndPass("qwe1@1.1", "Zaq12wsX");
		}
	}

	@AfterAll
	private static void deleting (@Autowired FindService findService) {
		findService.findAndDeleteFromEmailPassTable("qwe1@1.1");
	}
	
	@Test
	@DisplayName(value = "/home to /home for not registrated users")
	public void receiveHomePage() throws Exception {
		this.mockMvc.perform(get("/home")).andExpect(status().isOk()).andExpect(
				content().string(containsString("<form action=\"/login-handler\" method=\"post\">")));
	}

	@Test
	@DisplayName(value = " /profile/home to /home for not registrated users")
	public void redirectToHomePageForNotRegistratedUsers() throws Exception {
		this.mockMvc.perform(get("/profile/home")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/home"));
	}

	@Test
	@DisplayName(value = "POST /home with correct email and pass to /profile/home")
	public void redirectedToProfileHomeForUsersWithCorrectEmailAndPassword() throws Exception {
		this.mockMvc.perform(formLogin().loginProcessingUrl("/login-handler").user("email", "qwe1@1.1")
				.password("password", "Zaq12wsX")).andExpect(redirectedUrl("/profile/home"));
	}

	@Test
	@DisplayName(value = "POST /home with notcorrect email to /login-failed")
	public void redirectedToHomeForNotRegistratedUsers() throws Exception {
		this.mockMvc
				.perform(formLogin().loginProcessingUrl("/login-handler")
						.user("email", "notregistried@1.1").password("password", "Zaq12wsX"))
				.andExpect(redirectedUrl("/login-failed"));
	}

	@Test
	@DisplayName(value = "POST /home with notcorrect pass to /login-failed")
	public void redirectedToHomeForUserWithNotCorrectPassword() throws Exception {
		this.mockMvc
				.perform(formLogin().loginProcessingUrl("/login-handler").user("email", "qwe1@1.1")
						.password("password", "notCorrectPassword"))
				.andExpect(redirectedUrl("/login-failed"));
	}
	

	@Test
	@DisplayName(value = " /home to /profile/home for registrated users")
	@WithUserDetails("qwe1@1.1")
	public void redirectedToProfileHomeForRegistratedUsers() throws Exception {
		this.mockMvc.perform(get("/home")).andExpect(redirectedUrl("/profile/home"));
	}

}