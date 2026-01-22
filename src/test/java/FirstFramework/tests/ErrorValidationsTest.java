package FirstFramework.tests;

import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import org.testng.AssertJUnit;
import org.testng.IRetryAnalyzer;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FirstFramework.TestComponents.BaseTest;
import FirstFramework.pageobjects.CartPage;
import FirstFramework.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=FirstFramework.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException 
	{
		
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("anshika@gmail.com", "Iamqatester@000");   // validate error with invalid password
		
		//.ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
			
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException 
	{
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("praveen.framework@gmail.com", "Praveen@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
