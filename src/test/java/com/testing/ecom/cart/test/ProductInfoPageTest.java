package com.testing.ecom.cart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testing.ecom.cart.base.BasePage;

public class ProductInfoPageTest extends BasePage{
	@BeforeClass
	public void doLoginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"Macbook","MacBook Air",4},
			{"Macbook","MacBook Pro",4},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"iMac","iMac",3},
			{"Apple","Apple Cinema 30\"",6}
		};
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey,String productName,int imagesCount) {
		searchPage = accPage.performSearch(searchKey);
		proInfoPage = searchPage.selectProduct(productName);
		int actImagesCount = proInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("macbook");
		proInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap =  proInfoPage.getProductInfo();
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productPrice"), "$2,000.00");
		softAssert.assertAll();		
	}
	@DataProvider
	public Object[][] getAddToCartTestData() {
		return new Object[][] {
			{"macbook","MacBook Pro",2},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"imac","iMac",3}
		};
	}
	@Test(dataProvider = "getAddToCartTestData")
	public void addToCartTest(String searchKey,String productName,int quantity) {
		searchPage = accPage.performSearch(searchKey);
		proInfoPage = searchPage.selectProduct(productName);
		proInfoPage.enterQuantity(quantity);
		String actMsg = proInfoPage.addProductToCart();
		softAssert.assertTrue(actMsg.indexOf("Success")>=0);
		softAssert.assertTrue(actMsg.indexOf(productName)>=0);
		softAssert.assertEquals(actMsg,"Success: You have added "+productName+" to your shopping cart!");
		softAssert.assertAll();
		
	}

}
