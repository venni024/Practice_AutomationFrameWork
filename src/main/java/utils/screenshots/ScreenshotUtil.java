package utils.screenshots;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.driver.DriverManager;

public class ScreenshotUtil {
	
	public static void capture(String testName) throws IOException {

        TakesScreenshot ts =
            (TakesScreenshot) DriverManager.getWebDriver();

        File src = ts.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(src,
            new File("reports/screenshots/" + testName + ".png"));
    }

}
