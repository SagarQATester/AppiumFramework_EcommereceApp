package com.testComponents;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

	public  AndroidDriver driver;
	
	@BeforeMethod
	public void initiateDriver() throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();

		// Specify deviceName, platformName, and platformVersion
		caps.setCapability("deviceName", "RMX2151");
		caps.setCapability("platformName", "Android");

		caps.setCapability("automationName", "uiautomator2");
		// caps.setCapability("noReset", "false");

		// Specify the app downloadable path
		caps.setCapability("app", "F://SeleniumProject//MobileTesting//resources//General-Store.apk");

		// Initialize the driver
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	}
	
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"duration",2000));
	}
	
	public void scollingAction()
	{
		boolean canScrollMore;
		do {
			 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
		} while (canScrollMore);
	}
	
	public void scrollByText(String text)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));

	}
	
	public void swappingAction(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				 "elementId", ((RemoteWebElement) ele).getId(),
			    "direction", "left",
			    "percent", 0.75
			));
	}
	
	public void dragAnddropAction(WebElement element, int x, int y)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "endX", x,
			    "endY", y
			));
	}
	
	public void waitForElement(WebElement element)
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
