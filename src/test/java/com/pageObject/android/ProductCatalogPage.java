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

public class ProductCatalogPage extends AndroidActions {

	AndroidDriver driver;
	
	@FindBy(css="[text='Products']")
	private WebElement product;
	
	@FindBy(css="[text='ADD TO CART']")
	private WebElement addProduct;
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productName")
	private List< WebElement> products;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private List< WebElement> addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement viewCart;
	
	
	
	public ProductCatalogPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);


	}
	
	public boolean verifyUserLandOnProductCatalogPage()
	{
		waitForElement(product);
		return product.isDisplayed();
		
	}
	
	public void productAddToCart(String nameToBeAdded)
	{
		scrollByText(nameToBeAdded);
	     
	     for(int i=0;i<products.size();i++)
	     {
	    	String productName=products.get(i).getText();
	    	if(productName.equalsIgnoreCase(nameToBeAdded))
	    	{
	    		addToCart.get(i).click();   	
	    	}
	     }
		
	}
	public void addFirstTwoProductToTheCart()
	{
		addProduct.click();
		addProduct.click();
	}
	
	public void clickOnCartViewButton()
	{
		viewCart.click();
	}
	

}
