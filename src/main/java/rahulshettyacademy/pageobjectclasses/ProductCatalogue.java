package rahulshettyacademy.pageobjectclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver d;

	public ProductCatalogue(WebDriver d) {
		super(d);
		this.d = d;
		PageFactory.initElements(d, this);
	}

	// locate all products
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	// here we write this element because we need wait for visbility for this
	// element
	By FindBy = By.cssSelector(".mb-3");
	By addcart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage=By.cssSelector("#toast-container");

	// List<WebElement> products = d.findElements(By.cssSelector(".mb-3"));

	public List<WebElement> getProductsList() {

		waitForElementToBeAppear(FindBy);
		return products;

	}

	public WebElement getProductByName(String productname) {
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productname))
				.findFirst().orElse(null);
		return prod;

	}

	public void addProductToCart(String productname) {
		
		WebElement prod = getProductByName(productname);
		prod.findElement(addcart).click();
		waitForElementToBeAppear(toastmessage);

	}

}
