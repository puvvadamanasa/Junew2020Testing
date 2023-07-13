package com.testing.ecom.cart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.testing.ecom.cart.constants.AppConstants;
import com.testing.ecom.cart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By search = By.name("search");
	private By registerLink = By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getLoginPageTitle() {
		String loginPageTitle = eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("LoginPageTitle: "+loginPageTitle);
		return loginPageTitle;
	}
	public String getLoginPageURL() {
		String loginPageURL = eleUtil.waitForURLContainsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,AppConstants.LOGIN_PAGE_URL_VALUE);
		System.out.println("LoginPageURL: "+loginPageURL);
		return loginPageURL;
	}
	public boolean forgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	public boolean searchExist() {
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	public AccountsPage doLogin(String emailid,String pwd) {
		eleUtil.waitForElementVisible(email, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(emailid);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
