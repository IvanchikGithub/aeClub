package com.aeClub;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.aeClub.config.NavigationTestsForHomePage;
import com.aeClub.enums.CountryList;
import com.aeClub.enums.DenominationType;
import com.aeClub.enums.GenderType;
import com.aeClub.form.AccountForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.impl.CreateNewUserServiceImpl;
import com.aeClub.util.AccountExtraInfoBuilder;


@SpringBootTest(classes=NavigationTestsForHomePage.class)
public class CreateNewUserServiceImplTest {

	CreateNewUserServiceImpl createNewUserServiceImpl = new CreateNewUserServiceImpl();
	@Autowired
	CreateNewUserService createNewUserService;

	@Test
	public void testCreatingAccountExtraInfoBuilder() throws Exception {
		Class<CreateNewUserServiceImpl> classCreateNewUserServiceImpl = CreateNewUserServiceImpl.class;
		Method method = classCreateNewUserServiceImpl
				.getDeclaredMethod("buildingAccountExtraInfoBuilderFromFormsData", AccountForm.class);
		method.setAccessible(true);
		AccountForm accountForm = new AccountForm();
		accountForm.setNameForClub("Piter");
		accountForm.setBirthdateFromForm("1979-12-21");
		accountForm.setGender(GenderType.MAN.getName());
		accountForm.setCountry(CountryList.UNITED_STATES_OF_AMERICA.getName());
		accountForm.setDenomination(DenominationType.PENTECOSTAL_CHURCH.getName());
		Object result = method.invoke(createNewUserServiceImpl, accountForm);
		Assert.notNull((AccountExtraInfoBuilder) result, "AccountExtraInfoBuilder is null");
	}

	@Test
	public void testCreatingNewPairEmailAndPass() {
		createNewUserService.creatingNewPairEmailAndPass("", "");
		
	}

}
