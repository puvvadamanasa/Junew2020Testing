package com.testing.ecom.cart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testing.ecom.cart.base.BasePage;
import com.testing.ecom.cart.constants.AppConstants;
import com.testing.ecom.cart.utils.ExcelUtil;

public class RegisterPageTest extends BasePage{
	@BeforeClass
	public void regPageSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	public String getRandomEmail() {
		Random random = new Random(); 
		//String email = "automation"+random.nextInt(1000)+"@gmail.com";
		String email = "automation"+System.currentTimeMillis()+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
	  Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	  return regData;
	}
	@Test(dataProvider = "getRegTestData")
	public void userRegTest(String firstName,String lastName,String telephone,String password,String subscribe ) {
		Assert.assertTrue(registerPage.registerUser(firstName, lastName,getRandomEmail(), telephone, password, subscribe));
	}

}
