package com.aeClub.config;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
public class ValidationDataByRegistrationTest {

	@Autowired
	private MockMvc mockMvc;

	private static Stream<Object> correctEmailAndPasswordForRegistration() {
		Arguments[] data = { Arguments.of("qwe1@1.1", "Zaq12wsX", "Zaq12wsX"),
				Arguments.of("qwe2@1.1", "Xsw23edC", "Xsw23edC"),
				Arguments.of("qwe3@1.1", "Vfr45tgB", "Vfr45tgB"), };
		return Arrays.asList(data).stream();
	}

	@ParameterizedTest
	@MethodSource(value = "correctEmailAndPasswordForRegistration")
	@DisplayName(value = "/registration POST calls method creatingNewPairEmailAndPass()")
	public void goFutherWithCorrectEmailAndPass(String email, String password1, String password2)
			throws Exception {

		this.mockMvc
				.perform(post("/registration").param("email", email).param("password1", password1)
						.param("password2", password2))
				.andExpect(redirectedUrl("/profile/registrationMainInfo"));
	}

	private static Stream<Object> unCorrectEmailAndPasswordForRegistration() {
		Arguments[] data = { Arguments.of("qwe11.1", "Zaq12wsX", "Zaq12wsX"),
				Arguments.of("qwe2@1.1", "Xs23edC", "Xsw23edC"),
				Arguments.of("qwe3@1.1", "VfrtgB", "VfrtgB"), };
		return Arrays.asList(data).stream();
	}

	@ParameterizedTest
	@MethodSource(value = "unCorrectEmailAndPasswordForRegistration")
	@DisplayName(value = "/registration POST calls method creatingNewPairEmailAndPass()")
	public void goBackWithUncorrectEmailOrPassword(String email, String password1, String password2)
			throws Exception {

		this.mockMvc
				.perform(post("/registration").param("email", email).param("password1", password1)
						.param("password2", password2))
				.andExpect(content().string(
						containsString("form method=\"post\" th:object=\"${createEmailPassForm}")));
	}

}
