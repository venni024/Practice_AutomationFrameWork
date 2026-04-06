package ui.pages;

import org.openqa.selenium.By;
import base.BasePage;
import core.config.ConfigReader;

public class LoginPage extends BasePage {

    By username = By.id("userName");
    By password = By.id("password");

    public void login(String user, String pass) {

        driver.get(ConfigReader.get("url"));
    	driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
    }
}