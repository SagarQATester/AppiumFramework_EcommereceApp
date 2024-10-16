 package com.testSuites;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.pageObject.android.CartPage;
import com.pageObject.android.FillForm;
import com.pageObject.android.ProductCatalogPage;
import com.testComponents.BaseTest;

import io.appium.java_client.AppiumBy;

public class E_Commerece_FillForm extends BaseTest {

	SoftAssert softAssert=new SoftAssert();
	FillForm fillForm;
	ProductCatalogPage productCatalog;
	CartPage cartPage;
	
	@Test(enabled=true)
	public void TC_001_verifyUserAbletoFillTheForm() throws InterruptedException
	{
	    fillForm= new FillForm(driver);
		fillForm.fillFormWithFillingAllFileds("Rutuja Kumbhar", "female", "Argentina");		
	    productCatalog= new ProductCatalogPage(driver);
		Assert.assertTrue(productCatalog.verifyUserLandOnProductCatalogPage());

	}
	

	@Test(enabled=true)
	public void TC_002_VerifyToastMessageWithoutFillingNamefield() throws InterruptedException
	{

	    fillForm= new FillForm(driver);
		fillForm.fillFormWithoutFillingUsernameField("female", "Argentina");
		String actualToastMessage=fillForm.getToastMessage();
		 Assert.assertEquals(actualToastMessage, "Please enter your name");    

	}
	
	@Test(enabled=true)
	public void TC_003_verifyWhethertheProductAddedToCart() throws InterruptedException
	{
	        fillForm= new FillForm(driver);
			fillForm.fillFormWithFillingAllFileds("Rutuja Kumbhar", "female", "Argentina");		
		    productCatalog= new ProductCatalogPage(driver);
			Assert.assertTrue(productCatalog.verifyUserLandOnProductCatalogPage());   
			productCatalog.productAddToCart("Jordan 6 Rings");
			productCatalog.clickOnCartViewButton();
			cartPage= new CartPage(driver);			
		    softAssert.assertTrue(cartPage.verifyUserLandOnCartPage());
		    String cartProductName  =cartPage.getProductNameFromCartPage();
		    softAssert.assertEquals(cartProductName, "Jordan 6 Rings");
		    softAssert.assertAll();
     
     
	}
	@Test(enabled=true)
	public void TC_004_verifySumOfProductPrice() throws InterruptedException
	{
             fillForm= new FillForm(driver);
			fillForm.fillFormWithFillingAllFileds("Rutuja Kumbhar", "female", "Argentina");		
		    productCatalog= new ProductCatalogPage(driver);
			Assert.assertTrue(productCatalog.verifyUserLandOnProductCatalogPage());   
			productCatalog.addFirstTwoProductToTheCart();
			productCatalog.clickOnCartViewButton();
			cartPage= new CartPage(driver);	
			double productTotalSumPrice=cartPage.getTotalSumPriceOfAddedProduct();
			double productTotalPrice=cartPage.getTotalSumPriceOfAddedProduct();
          // verify product price
       
          Assert.assertEquals(productTotalSumPrice, productTotalPrice);
    
     
	}
	
	@Test
	public void TC_005_verifyUserAbleToCompletePurchaseOrder() throws InterruptedException
	{
        fillForm= new FillForm(driver);
		fillForm.fillFormWithFillingAllFileds("Rutuja Kumbhar", "female", "Argentina");		
	    productCatalog= new ProductCatalogPage(driver);
		Assert.assertTrue(productCatalog.verifyUserLandOnProductCatalogPage());   
		productCatalog.addFirstTwoProductToTheCart();
		productCatalog.clickOnCartViewButton();
		cartPage= new CartPage(driver);	
		cartPage.selectCheckbox_SendMeEmailOnDiscountRelated();
		cartPage.readTermsAndConditions();
		cartPage.clickOnCompletePurchasebutton();  
       

    
     
	}
	
	
	
}
