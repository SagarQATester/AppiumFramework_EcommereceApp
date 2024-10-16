package com.pageObject.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;
	
	@FindBy(css="[text='Cart']")
	private WebElement cart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private WebElement cartProductName;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> cartProductprices;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement productTotalPrice;
	
	@AndroidFindBy(className ="android.widget.CheckBox")
	private WebElement checkbox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement closeTerms;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement purchaseProduct;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}
	
	public boolean verifyUserLandOnCartPage()
	{
	     waitForElement(cart);
	    return cart.isDisplayed();
	}
	
	public String getProductNameFromCartPage()
	{
		return cartProductName.getText();
	}
	
	public double getTotalSumPriceOfAddedProduct()
	{
	    double sum=0;
	      
	      for(int i=0;i<cartProductprices.size();i++)
	      {
	    	  String price=cartProductprices.get(i).getText();
	    	  Double formattedPrice=Double.parseDouble(price.substring(1)) ;
	    	  System.out.println(formattedPrice);
	    	  sum=sum+formattedPrice;
	    	  System.out.println(sum);  	  
	    	  
	      }
	      return sum;
	}
	public double getProductPrice()
	{
	       String totalPrice=  driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	       double formattedTotalPrice=Double.parseDouble(totalPrice.substring(1));
	       System.out.println(formattedTotalPrice);
	       return formattedTotalPrice;
	}
	
	public void selectCheckbox_SendMeEmailOnDiscountRelated()
	{
		checkbox.click();
	}
	public void readTermsAndConditions()
	{
		longPressAction(terms);
		closeTerms.click();
	}
	
	public void clickOnCompletePurchasebutton()
	{
		purchaseProduct.click();
	}

}
