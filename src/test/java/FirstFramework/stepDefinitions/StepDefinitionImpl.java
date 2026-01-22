package FirstFramework.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import FirstFramework.TestComponents.BaseTest;
import FirstFramework.pageobjects.CartPage;
import FirstFramework.pageobjects.CheckoutPage;
import FirstFramework.pageobjects.ConfirmationPage;
import FirstFramework.pageobjects.LandingPage;
import FirstFramework.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();	
	}
	
//	 Given Logged in with username "shetty@gmail.com" and password "Iamtester@000"
	 
	@Given("^Logged in with username (.+) and password (.+)$")        // (.+)  -  these are regular expression to accept any dynamic string
	public void logged_in_username_and_password(String username, String password)
	{
	   productCatalogue = landingPage.loginApplication(username, password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		
	    confirmationPage = checkoutPage.submitOrder();
		
	}
	
//	Then "Thankyou for the order." message is display on ComfirmationPage
	@Then("{string} message is display on ComfirmationPage")
	public void message_display_confirmationPage(String string)
	{
		String confirmMsg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed") 
	public void message_is_displayed(String string) throws InterruptedException
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
	
	
	
}
