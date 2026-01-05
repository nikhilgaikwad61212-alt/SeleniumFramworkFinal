package rahulshettyacademy.pageobjectclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver d;

	public CartPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartproducts;

	public List<WebElement> cartproducts1() {

		return cartproducts;
	}

	public boolean verifyProductDisplay(String productname) {
		boolean status = cartproducts1().stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));

		return status;

	}

	// check out button

	@FindBy(css = ".btn.btn-primary:nth-child(1):last-child")
	WebElement checkoutbutton;

	public CheckOutPage goToCheckOut() {
		checkoutbutton.click();
CheckOutPage choupage=new CheckOutPage(d);
return choupage;
	}

}
