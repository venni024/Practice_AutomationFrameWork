package core.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	
	private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
	
	public static WebDriver getWebDriver() {
		return tdriver.get();
	}
	
	public static void setDriver(WebDriver driverInstance) {
		tdriver.set(driverInstance);
	}
	
	public static void quitDriver() {
		tdriver.get().quit();
		tdriver.remove();
	}

}
