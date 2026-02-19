package FirstFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FirstFramework.pageobjects.CartPage;
import FirstFramework.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".btn.btn-custom[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
//	@FindBy(css = ".btn.btn-custom[routerlink='/dashboard/myorders']")
//	WebElement orderHeader;

	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	
	}
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage()
	{
		 By ordersLocator = By.cssSelector("button[routerlink*='myorders']");
		    
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		    WebElement ordersBtn = wait.until(
		            ExpectedConditions.elementToBeClickable(ordersLocator)
		    );

		    ordersBtn.click();
		    return new OrderPage(driver);
		
//		waitForElementToAppear(By.cssSelector(".btn.btn-custom[routerlink='/dashboard/myorders']"));
//		
//		orderHeader.click();
//		OrderPage orderPage = new OrderPage(driver);
//		return orderPage;
		
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
//	wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
	
}
