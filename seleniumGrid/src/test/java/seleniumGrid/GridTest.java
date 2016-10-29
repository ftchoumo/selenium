package seleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GridTest {

WebDriver driver;
String baseUrl, nodeURL;

@BeforeTest
public void setup() throws MalformedURLException{
	baseUrl="https://www.youtube.com/";
	nodeURL="http://192.168.0.8:5555/wd/hub";
	DesiredCapabilities capability=DesiredCapabilities.firefox();
	capability.setBrowserName("firefox");
	capability.setPlatform(Platform.ANY);
	driver= new RemoteWebDriver(new URL(nodeURL), capability);
	
}
@AfterTest
public void aftertest(){
	driver.quit();

}
@Test
public void testmethod(){
	driver.get(baseUrl);

}

}
