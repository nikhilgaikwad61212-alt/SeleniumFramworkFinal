package rahulshettyacademy.pageobjectclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver d;

	public ConfirmationPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmmessage;

	public String getConfirmText() {
		String confirmationtext = confirmmessage.getText().toUpperCase();
		return confirmationtext;

	}

}
