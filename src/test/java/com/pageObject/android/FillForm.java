package com.pageObject.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FillForm extends AndroidActions {

	AndroidDriver driver;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement name;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement female;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement male;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement country;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsGo;
	
	@AndroidFindBy(xpath="(//android.widget.Toast)[1]")
	private WebElement toastMessage;
	
	
	public FillForm(AndroidDriver driver) {
         super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void enterUserName(String username)
	{
		name.sendKeys(username);
		driver.hideKeyboard();
	}
	
	public void selectGender(String gender)
	{
		if(gender.equalsIgnoreCase("female"))
		    female.click();
		else
			male.click();
	}
	public void selectCountryName(String countryName)
	{
		country.click();
		scrollByText(countryName);
		driver.findElement(By.cssSelector("[text='"+countryName+"']")).click();
	}
	public void clickOnLetsGo() 
	{
		letsGo.click();
	}
	
	public void fillFormWithFillingAllFileds(String username, String gender, String countryName)
	{
		enterUserName(username);
		selectGender(gender);
		selectCountryName(countryName);
		clickOnLetsGo();
		
	}
	public void fillFormWithoutFillingUsernameField( String gender, String countryName)
	{
		
		selectGender(gender);
		selectCountryName(countryName);
		clickOnLetsGo();
		
	}
	public String getToastMessage()
	{
		return toastMessage.getAttribute("name");
	}
}
