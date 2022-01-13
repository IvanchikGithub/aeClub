package com.aeClub;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.aeClub.config.AEApplication;
import com.aeClub.enums.Countries;
import com.aeClub.enums.DenominationTypes;
import com.aeClub.enums.GenderTypes;
import com.aeClub.form.AccountForm;
import com.aeClub.service.CreateNewUserService;
import com.aeClub.service.impl.CreateNewUserServiceImpl;
import com.aeClub.util.AccountExtraInfoBuilder;


@SpringBootTest(classes=AEApplication.class)
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
		accountForm.setGender(GenderTypes.MAN.getName());
		accountForm.setCountry(Countries.UNITED_STATES_OF_AMERICA.getName());
		accountForm.setDenomination(DenominationTypes.PENTECOSTAL_CHURCH.getName());
		Object result = method.invoke(createNewUserServiceImpl, accountForm);
		Assert.notNull((AccountExtraInfoBuilder) result, "AccountExtraInfoBuilder is null");
	}

}
