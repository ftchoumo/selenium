package mavenP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testi {
	WebDriver driver;
	String Url= "https://www.youtube.com/";
	
	@BeforeTest
	public void tests() throws Exception{
		driver= new FirefoxDriver();
	}

	@Test
	public void testam() throws Exception{
		driver.get(Url);
		driver.manage().window().maximize();
		System.out.println("youtube is open");
	}
	@AfterTest
	public void soit() throws Exception{
		driver.close();
	}
}
