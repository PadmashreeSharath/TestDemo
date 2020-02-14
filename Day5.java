package GuruBank99Automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Day5 {
	static WebDriver driver;

	@BeforeSuite
	public static void setup(){
		System.setProperty(Utils.key, Utils.value);
		String baseUrl=Utils.baseUrl;

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Utils.timeout, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@DataProvider
	public Object[][] dp(){
		String [][] data = new String[1][2];
		data[0][0]="mngr243706";
		data[0][1]="sydAhAb";
		return data;

	}

	@Test(dataProvider = "dp")
	public static void TestCase1(String username, String password) throws Exception {
		String actualTitle;
		String actualBoxtitle;
		String s =null;

		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);

		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();

		try{ 
			Alert alt = driver.switchTo().alert();
			actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
			if (actualBoxtitle.contains(Utils.EXPECT_ERROR)) { // Compare Error Text with Expected Error Value
				System.out.println("Test case SS1: Passed"); 
			} else {
				System.out.println("Test case SS1: Failed");
			}
		}    
		catch (NoAlertPresentException Ex){
			Thread.sleep(30000);
			actualTitle = driver.getTitle();

			// On Successful login compare Actual Page Title with Expected Title
			if (actualTitle.contains(Utils.EXPECT_TITLE)) {
				Thread.sleep(30000);
				s=driver.findElement(By.xpath("//table/tbody/tr/td/")).getText();
				System.out.println("Manager ID of home page verification : "+s);
				if(s.contains(username)){
					System.out.println(s.contains(username));
					System.out.println("Test case SS: Passed");
				}
			} else {
				System.out.println("Test case SS: Failed");
			}
		} 

	} 


	@AfterSuite
	public void tearDown(){
		driver.quit();
	}
}
