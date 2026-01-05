package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjectclasses.CartPage;
import rahulshettyacademy.pageobjectclasses.CheckOutPage;
import rahulshettyacademy.pageobjectclasses.ConfirmationPage;
import rahulshettyacademy.pageobjectclasses.OrderPage;
import rahulshettyacademy.pageobjectclasses.ProductCatalogue;

public class SubmitOrderTestF extends BaseTest {
	// String productname = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" })
//	public void SubmitOrder(String email, String pass, String productname) throws IOException, InterruptedException (use for approach 1)
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue ProductCatalogue = lp.loginApplication(input.get("email"), input.get("pass"));

		// find product & clcik on add button and wait for toaster message
		List<WebElement> products = ProductCatalogue.getProductsList();
		ProductCatalogue.getProductByName(input.get("productname"));
		ProductCatalogue.addProductToCart(input.get("productname"));
		// click on cart button
		CartPage cp = ProductCatalogue.goToCart();
		// check product in cart
		boolean status = cp.verifyProductDisplay(input.get("productname"));
		Assert.assertTrue(status);
		// click on check order
		CheckOutPage choupage = cp.goToCheckOut();
		// enter country name and click on place order
		choupage.Selectcountry("india");
		ConfirmationPage cnfpag = choupage.Placeorder();
		// confirmation
		String confirmationtext = cnfpag.getConfirmText();
		Assert.assertEquals(confirmationtext, "THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods = { "SubmitOrder" }, enabled = false)
	public void OrderHistoryPage() throws InterruptedException {

		// ZARA COAT 3
		ProductCatalogue ProductCatalogue = lp.loginApplication("Nikhil6799@gmail.com", "Nikhil@6799");
		OrderPage op = ProductCatalogue.goToOrderPage();
		// Assert.assertTrue(op.verifyOrderDisplay(productname));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// Approach 1
		// return new Object [][] {{"Nikhil6799@gmail.com", "Nikhil@6799","ZARA COAT
		// 3"},{"nikhilgaikwad6799@gmail.com","Nikhil@6799","ADIDAS ORIGINAL"}};

		/*
		 * Approach 2
		 * 
		 * HashMap<String, String> map = new HashMap<String, String>(); map.put("email",
		 * "Nikhil6799@gmail.com"); map.put("pass", "Nikhil@6799");
		 * map.put("productname", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "nikhilgaikwad6799@gmail.com"); map1.put("pass",
		 * "Nikhil@6799"); map1.put("productname", "ADIDAS ORIGINAL");
		 * 
		 * return new Object[][] { { map }, { map1 } };
		 */
		//

		// Approach 3 using json file

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseData.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
