package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTestF {

	public static void main(String[] args) throws InterruptedException {

		String productname = "ZARA COAT 3";
		WebDriver d = new ChromeDriver();
		d.get("https://rahulshettyacademy.com/client/#/auth/login");
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(7));
		// login page

		d.findElement(By.cssSelector("[id*='userEmail']")).sendKeys("Nikhil6799@gmail.com");
		d.findElement(By.cssSelector("#userPassword")).sendKeys("Nikhil@6799");
		d.findElement(By.xpath("//input[@value='Login']")).click();

		// find product

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = d.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productname))
				.findFirst().orElse(null);

		// click on add button
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// confimation
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// click on cart button
		Thread.sleep(3000);
		d.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// check product in cart

		List<WebElement> cartproducts = d.findElements(By.cssSelector(".cartSection h3"));
		boolean status = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(status);

		// click on check order
		d.findElement(By.cssSelector(".btn.btn-primary:nth-child(1):last-child")).click();
		// enter country name and click on place order
		Actions a = new Actions(d);
		a.sendKeys(d.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		d.findElement(By.cssSelector(".list-group button:last-of-type")).click();
		JavascriptExecutor js = (JavascriptExecutor) d;
		js.executeScript("document.body.style.zoom='50%'");
		d.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();

		// wait until confirmation
		wait.until(ExpectedConditions.visibilityOf(d.findElement(By.cssSelector("#toast-container"))));

		// confirmation
		String confirmationtext = d.findElement(By.cssSelector(".hero-primary")).getText().toUpperCase();
		Assert.assertEquals(confirmationtext, "THANKYOU FOR THE ORDER.");
		d.close();

	}

}
