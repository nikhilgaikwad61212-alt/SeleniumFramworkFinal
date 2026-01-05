package rahulshettyacademy.TestComponent;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjectclasses.LandingPage;

public class BaseTest {

	public WebDriver d;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException {

		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		p.load(fi);
		// String browserName = p.getProperty("browser");

		String browserName = System.getProperty("browser") != null ? (System.getProperty("browser"))
				: (p.getProperty("browser"));

		if (browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			
			if (browserName.contains("headless")) 
			{

				options.addArguments("headless");
			}

			d = new ChromeDriver(options);
			d.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			d = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			d = new EdgeDriver();
		}

		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return d;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String fp) throws IOException {

		// read json to string

		String jsoncontent = FileUtils.readFileToString(new File(fp), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
//System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseData.json"
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		d = initializeDriver();
		lp = new LandingPage(d);
		lp.goTo();
		return lp;
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {

		d.close();
	}

	public String getScreenshot(String testcasename, WebDriver d) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) d;
		File src = ts.getScreenshotAs(OutputType.FILE);

		File file = new File(System.getProperty("user.dir") + "//reports//" + testcasename + ".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir") + "//reports//" + testcasename + ".png";
	}

}
