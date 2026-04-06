package core.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import core.reporting.ExtentManager;
import core.reporting.ExtentTestManager;

public class TestListener implements ITestListener {
	
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
	

}
