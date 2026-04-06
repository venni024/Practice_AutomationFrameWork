package dr.ert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class App 
{
    public static void main( String[] args )
    {
//    	String s  = "durga";
//    	s.concat("software");
//    	System.out.println(s);
//    	
//    	StringBuffer sb = new StringBuffer("naresh");
//    	sb.append("tech");
//    	System.out.println(sb);
    	
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\kburada\\Kiran\\Softwares\\chromedriver_win32\\chromedriver.exe");
    	//WebDriverManager.chromedriver().setup();
    	WebDriver driver = new ChromeDriver();
		// Navigate to the demoqa website
		driver.get("https://www.flipkart.com/");
		
		driver.quit();
    	
    	
        
    }
}
