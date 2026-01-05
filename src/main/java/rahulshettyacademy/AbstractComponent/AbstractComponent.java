package rahulshettyacademy.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjectclasses.CartPage;
import rahulshettyacademy.pageobjectclasses.OrderPage;

public class AbstractComponent {

	WebDriver d;

	public AbstractComponent(WebDriver d) {
		this.d = d;
		PageFactory.initElements(d, this);
	}

	public void waitForElementToBeAppear(By FindBy) {
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartbutton;

	public CartPage goToCart() throws InterruptedException {
		Thread.sleep(3000);
		cartbutton.click();
		CartPage cp = new CartPage(d);
		return cp;
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderbutton;
	
	public OrderPage goToOrderPage() throws InterruptedException {
		Thread.sleep(3000);
		orderbutton.click();
		OrderPage op = new OrderPage(d);
		return op;
	}
	
	
	

}
