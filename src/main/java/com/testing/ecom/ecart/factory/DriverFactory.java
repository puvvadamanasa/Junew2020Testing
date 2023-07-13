package com.testing.ecom.ecart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.aspectj.util.FileUtil;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager oManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	/**
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		highlight = prop.getProperty("highlight").trim();
		System.out.println(highlight);
		oManager = new OptionsManager(prop);
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("BrowserName: "+browserName);
		String url = prop.getProperty("url").trim();
		System.out.println("URL: "+url);
		System.out.println("BrowserName: "+browserName);
		if(browserName.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver(oManager.getChromeOptions());
			tldriver.set(new ChromeDriver(oManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver(oManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(oManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver(oManager.getEdgeOptions());
			tldriver.set(new EdgeDriver(oManager.getEdgeOptions()));
		}
		else {
			System.out.println("Please pass right browserName");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);
		return getDriver();
	}
	/**
	 * get local thread copy of driver
	 */
	public synchronized static WebDriver getDriver() {
		return tldriver.get();
	}
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream fi = new FileInputStream("./src/main/resources/config/config.properties");
			prop.load(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;	
	}
	public static String getScreenShotAs() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
	

}
