package com.testing.ecom.cart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testing.ecom.cart.base.BasePage;
import com.testing.ecom.cart.constants.AppConstants;

public class AccountsPageTest extends BasePage{
	@BeforeClass
	public void doLoginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actTitle, "My Account");
	}
	
	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccountsPageURL();
		Assert.assertTrue(actURL.contains("index.php?route=account/account"));
	}
	@Test 
	public void isSearchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	@Test 
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	@Test
	public void AccountsPageHeadersCountTest() {
		Assert.assertEquals(accPage.getAccountsPageHeadersList().size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void AccountsPageHeadersValueTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsPageHeadersList();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_EXPECTED_HEADERS_LIST);
	}
	@DataProvider
	public Object[][] getProduct() {
		return new Object[][] {
			{"Macbook"},
			{"Samsung"},
			{"iMac"},
			{"Apple"}
		};
	}
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"Macbook","MacBook Air"},
			{"Macbook","MacBook Pro"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"iMac","iMac"},
			{"Apple","Apple Cinema 30\""}
		};
	}
	@Test(dataProvider = "getProduct")
	public void searchProductCountTest(String searchKey) {
		searchPage = accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount()>0);
	}
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey,String productName) {
		searchPage = accPage.performSearch(searchKey);
		if(searchPage.getSearchProductsCount()>0) {
			proInfoPage = searchPage.selectProduct(productName);
			Assert.assertEquals(proInfoPage.getProductHeaderValue(), productName);
		}
		
	}

}
