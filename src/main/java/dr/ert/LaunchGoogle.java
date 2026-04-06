package dr.ert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchGoogle {
	
    public static void main(String[] args) {

        // Selenium Manager will handle driver automatically
        WebDriver driver = new ChromeDriver();

        // Open Google
        driver.get("https://demoqa.com/");

        // Print title
        System.out.println("Title: " + driver.getTitle());
        
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Close browser
        driver.quit();
    }

}
