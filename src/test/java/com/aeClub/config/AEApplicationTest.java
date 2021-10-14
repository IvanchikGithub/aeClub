package com.aeClub.config;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.FindService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class AEApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CreateNewUserService createNewUserService;

	@Autowired
	private FindService findService;

	@BeforeEach
	private void init() {
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
	}

	@Test
	public void receiveHomePage() throws Exception {
		this.mockMvc.perform(get("/home")).andExpect(status().isOk()).andExpect(
				content().string(containsString("<form action=\"/login-handler\" method=\"post\">")));
	}

	@Test
	public void redirectToHomePageForNotRegistratedUsers() throws Exception {
		this.mockMvc.perform(get("/profile/home")).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/home"));
	}

	// @Sql (value= {"/creatingUsers.sql"}, executionPhase =
	// ExecutionPhase.BEFORE_TEST_METHOD)
	// @Sql (value= {"/deletingUsers.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void redirectedToHomeProfileForRegistratedUsers() throws Exception {
		this.mockMvc.perform(formLogin().loginProcessingUrl("/login-handler").user("email", "qwe1@1.1")
				.password("password", "Zaq12wsX")).andDo(print());
	}
}