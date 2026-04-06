package base;

import org.openqa.selenium.WebDriver;

import core.driver.DriverManager;

public abstract class BasePage {
	
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getWebDriver();
        
        if (driver == null) {
            throw new RuntimeException("Driver is NULL — not initialized!");
        }
    }

}
