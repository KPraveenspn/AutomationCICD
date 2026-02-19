package FirstFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FirstFramework.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	

	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css = ".row h5")
	private List<WebElement> cartProduct;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProduct.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	


}
