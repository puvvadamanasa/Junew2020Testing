package com.testing.ecom.cart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testing.ecom.cart.constants.AppConstants;
import com.testing.ecom.cart.utils.ElementUtil;


public class SearchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchProductResults = By.xpath("//div[@id='content']//div[contains(@class,'product-layout')]");
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public int getSearchProductsCount() {
		int count = eleUtil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product count: "+count);
		return count;
	}
	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		System.out.println("product Name: "+productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}
