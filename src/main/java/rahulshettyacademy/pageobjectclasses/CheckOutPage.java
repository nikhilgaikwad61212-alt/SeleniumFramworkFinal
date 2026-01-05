package rahulshettyacademy.pageobjectclasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver d;

	public CheckOutPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".list-group button:last-of-type")
	WebElement selectcountry;

	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	WebElement placeorder;

	public void Selectcountry(String cou) {

		Actions a = new Actions(d);
		a.sendKeys(country, cou).build().perform();
		selectcountry.click();
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("document.body.style.zoom='50%'");

	}

	public ConfirmationPage Placeorder() {

		placeorder.click();
		return new ConfirmationPage(d);
	}
}
