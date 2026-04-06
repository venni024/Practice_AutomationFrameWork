package base;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import core.config.ConfigReader;
import core.driver.DriverFactory;
import core.driver.DriverManager;
import core.reporting.ExtentTestManager;
import utils.screenshots.ScreenshotUtil;
import core.listeners.*;

@Listeners({TestListener.class})
public class BaseTest {

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("Chrome") String Browser, Method method) {

        WebDriver driver = DriverFactory.createDriver(Browser);
        DriverManager.setDriver(driver);

        ExtentTestManager.startTest(method.getName());

        driver.get(ConfigReader.get("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {

            ScreenshotUtil.capture(result.getName());

            ExtentTestManager.getTest().fail(result.getThrowable());
        }
    }
}