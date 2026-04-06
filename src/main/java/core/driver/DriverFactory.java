package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public static WebDriver createDriver(String browser) {
		
//		String browser = ConfigReader.get("browser");
		switch (browser) {
			case "chrome":
				return new ChromeDriver();
				
			case "firefox":
				return new FirefoxDriver();
		
			default:
				throw new RuntimeException("Invalid Browser");
		
		}
	}

}
