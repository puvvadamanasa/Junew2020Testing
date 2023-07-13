package com.testing.ecom.cart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testing.ecom.cart.constants.AppConstants;
import com.testing.ecom.cart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By logoutLink = By.linkText("Logout");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	public String getAccountsPageTitle() {
		String accPageTitle = eleUtil.waitForTitleIsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Account Page Title: "+accPageTitle);
		return accPageTitle;
	}
	public String getAccountsPageURL() {
		String accPageURL = eleUtil.waitForURLContainsandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_VALUE);
		System.out.println("Account Page URL: "+accPageURL);
		return accPageURL;
	}
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	public List<String> getAccountsPageHeadersList() {
		List<WebElement> accHeaderList = eleUtil.waitForElementsVisible(accHeaders, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		List<String> accHeaderValList = new ArrayList<>();
		for(WebElement e:accHeaderList) {
			String text = e.getText();
			accHeaderValList.add(text);
		}
		System.out.println("accHeaderValList: "+accHeaderValList);
		return accHeaderValList;
		
	}
	public SearchPage performSearch(String searchKey) {
		if(isSearchExist()) {
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchPage(driver);
		}
		else {
			System.out.println("Search not exists");
			return null;
		}
		
	}

}
