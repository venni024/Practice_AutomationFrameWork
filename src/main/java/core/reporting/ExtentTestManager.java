package core.reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void startTest(String testName) {
        test.set(ExtentManager.getInstance().createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

}
