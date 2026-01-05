package rahulshettyacademy.pageobjectclasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver d;

	public OrderPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productnames;

	public boolean verifyOrderDisplay(String productname) {
		boolean status = productnames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));

		return status;

	}

}
