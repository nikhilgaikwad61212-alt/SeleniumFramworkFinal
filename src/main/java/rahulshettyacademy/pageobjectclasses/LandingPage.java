package rahulshettyacademy.pageobjectclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent
{

	WebDriver d;

	public LandingPage(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

	// user email
	@FindBy(css = "[id*='userEmail']")
	WebElement userEmail;

	// user password
	@FindBy(css = "#userPassword")
	WebElement userPassword;

	// login button
	@FindBy(xpath = "//input[@value='Login']")
	WebElement Login;
	
	//this is for error toaster
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String pass) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		Login.click();
		ProductCatalogue ProductCatalogue = new ProductCatalogue(d);
		return ProductCatalogue;

	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	

	public void goTo() {

		d.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

}
