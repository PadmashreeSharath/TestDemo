package GuruBank99Automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Day1 {
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
	@Test
	public static void TestCase1() throws Exception {
		String actualTitle;
		String[][] testData = Utils.getDataFromExcel(Utils.FILE_PATH);
		String username, password;
		String actualBoxtitle;
		for (int i = 0; i < testData.length; i++) {
			username = testData[i][0]; // get username
			password = testData[i][1]; // get password
			
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
					System.out.println("Test case SS1[" + i + "]: Passed"); 
				} else {
					System.out.println("Test case SS1[" + i + "]: Failed");
				}
			}    
			catch (NoAlertPresentException Ex){
				Thread.sleep(30000);
				actualTitle = driver.getTitle();
				
				// On Successful login compare Actual Page Title with Expected Title
				if (actualTitle.contains(Utils.EXPECT_TITLE)) {
					System.out.println("Test case SS[" + i + "]: Passed");
				} else {
					System.out.println("Test case SS[" + i + "]: Failed");
				}
			} 
		
		} 
	}
	
	@AfterSuite
	public void tearDown(){
		driver.quit();
	}
}

