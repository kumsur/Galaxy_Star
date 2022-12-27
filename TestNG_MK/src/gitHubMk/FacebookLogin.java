package gitHubMk;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FacebookLogin {
	
	WebDriver driver;
	PomSource pom;
	@Test
	public void launchFb() throws IOException {
		
		driver.get("https://www.facebook.com");
		pom=new PomSource(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FileInputStream fis=new FileInputStream("./TestData/TestData.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheet("Sheet1");
		XSSFRow row=sheet.getRow(0);
		pom.getUsernameTF().sendKeys(row.getCell(0).toString());
		pom.getPassTF().sendKeys(row.getCell(1).toString());
		pom.getLoginBtn().click();		
	}
	
	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./driverfiles/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	@AfterMethod
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}
	
	

}
