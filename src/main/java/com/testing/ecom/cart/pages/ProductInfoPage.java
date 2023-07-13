package com.testing.ecom.cart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testing.ecom.cart.constants.AppConstants;
import com.testing.ecom.cart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader = By.tagName("h1");
	private Map<String, String> productInfoMap;
	private By images = By.cssSelector("ul.thumbnails img");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMsg = By.cssSelector("div.alert.alert-success");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderValue() {
		String productHeaderVal = eleUtil.getElementText(productHeader);
		System.out.println("Product Header value: "+productHeaderVal);
		return productHeaderVal;
	}
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(images, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Images count: "+imagesCount);
		return imagesCount;
	}
	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		//productInfoMap = new LinkedHashMap<String, String>(); insertion order is preserved
		//productInfoMap = new TreeMap<String, String>(); sort order
		productInfoMap.put("productName", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productInfoMap);
		return productInfoMap;	
	}
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for(WebElement e:metaList) {
			String meta = e.getText();
			System.out.println(meta);
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
		
	}
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPricingData);
		for(WebElement e:priceList) {
			String price = priceList.get(0).getText();
			String exTax = priceList.get(1).getText();
			String exTaxVal = exTax.split(":")[1].trim();
			productInfoMap.put("productPrice", price);
			productInfoMap.put("exTax", exTaxVal);	
		}
		
	}
	public void enterQuantity(int quant) {
		System.out.println("Product quantity: "+quant);
		eleUtil.doSendKeys(quantity,String.valueOf(quant));
	}
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String succMsg = eleUtil.waitForElementVisible(cartSuccessMsg, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		StringBuilder sb = new StringBuilder(succMsg);
		String mesg = succMsg.substring(0, succMsg.length()-1).replace("\n", "");
		System.out.println(mesg);
		return mesg;
	}
	

}
