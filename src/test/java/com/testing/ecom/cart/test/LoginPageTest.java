package com.testing.ecom.cart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testing.ecom.cart.base.BasePage;

public class LoginPageTest extends BasePage{
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, "Account Login");
	}
	@Test(priority=2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains("index.php?route=account/login"));
	}
	@Test(priority=3)
	public void searchExistTest() {
		Assert.assertTrue(loginPage.searchExist());
	}
	@Test(priority=4)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.forgotPwdLinkExist());
	}
	@Test(priority=5)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		System.out.println("UserName: "+prop.getProperty("username"));
		System.out.println("Password: "+prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	

}
