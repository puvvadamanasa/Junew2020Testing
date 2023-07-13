package com.testing.ecom.cart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.testing.ecom.cart.pages.AccountsPage;
import com.testing.ecom.cart.pages.LoginPage;
import com.testing.ecom.cart.pages.ProductInfoPage;
import com.testing.ecom.cart.pages.RegisterPage;
import com.testing.ecom.cart.pages.SearchPage;
import com.testing.ecom.ecart.factory.DriverFactory;
import com.testing.ecom.ecart.factory.OptionsManager;

public class BasePage {
	DriverFactory df;
	WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected Properties prop;
	protected SearchPage searchPage;
	protected ProductInfoPage proInfoPage;
	protected RegisterPage registerPage;
	OptionsManager opManager;
	protected SoftAssert softAssert;
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		accPage = new AccountsPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
