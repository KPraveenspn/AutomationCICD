package FirstFramework.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import FirstFramework.TestComponents.BaseTest;
import FirstFramework.pageobjects.CartPage;
import FirstFramework.pageobjects.CheckoutPage;
import FirstFramework.pageobjects.ConfirmationPage;
import FirstFramework.pageobjects.LandingPage;
import FirstFramework.pageobjects.OrderPage;
import FirstFramework.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.sdk.metrics.internal.state.ObjectPool;

public class SubmitOrderTest extends BaseTest{

	//String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException 
	{
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		
		CartPage cartPage = productCatalogue.goToCartPage();
		 Thread.sleep(3000);
	//	Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
	//	Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMsg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
			
	}
	
	//To verify ZARA COAT 3 is displaying in orders page
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		//ZARA COAT 3;
		String productName = "ADIDAS ORIGINAL";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("testeradmin@gmail.com", "Tester@123");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	//Data provider & Parameterization 
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//here object is generic data type which accept any kind of data type(ex- int or string)
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "praveen.framework@gmail.com");
//		map.put("password", "Praveen@123");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		
		List<HashMap<String, String>> data = getJsonDataToMap("/Users/praveenkumar/eclipse-workspace/SeleniumFrameworkDesign/src/test/java/FirstFramework/data/PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};    
		
		
	//	return new Object[][] {{"praveen.framework@gmail.com", "Praveen@123","ZARA COAT 3"}, {map1}};  
	}
	
	
	
		

}
