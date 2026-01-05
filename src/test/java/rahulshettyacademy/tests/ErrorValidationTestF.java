package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.TestComponent.Retry;
import rahulshettyacademy.pageobjectclasses.CartPage;
import rahulshettyacademy.pageobjectclasses.CheckOutPage;
import rahulshettyacademy.pageobjectclasses.ConfirmationPage;
import rahulshettyacademy.pageobjectclasses.LandingPage;
import rahulshettyacademy.pageobjectclasses.ProductCatalogue;

public class ErrorValidationTestF extends BaseTest {

	@Test(groups = {"errorhandling"}, retryAnalyzer=Retry.class)
	public void LoginValidation() throws IOException, InterruptedException {

		String productname = "ZARA COAT 3";

		ProductCatalogue ProductCatalogue = lp.loginApplication("Nikhil6799@gmail.com", "Nikhil@6799sqwede");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());


 	}
	
	@Test
	public void submitOrderValidation() throws InterruptedException {
		String productname = "ZARA COAT 3";

			ProductCatalogue ProductCatalogue = lp.loginApplication("nikhilgaikwad6799@gmail.com", "Nikhil@6799");

			// find product & clcik on add button and wait for toaster message
			List<WebElement> products = ProductCatalogue.getProductsList();
			ProductCatalogue.getProductByName(productname);
			ProductCatalogue.addProductToCart(productname);
			// click on cart button
			CartPage cp = ProductCatalogue.goToCart();
			// check product in cart
			boolean status = cp.verifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(status);
	}

}
