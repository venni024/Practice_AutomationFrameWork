package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import ui.pages.LoginPage;
import utils.excel.ExcelReader;

public class LoginTest extends BaseTest {

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() {

	    return ExcelReader.getData(
	        "src/test/resources/testdata/testdata.xlsx",
	        "Login"
	    );
	}
	
	@Test(dataProvider = "loginData")
    public void verifyLogin(String user, String pass) {

        LoginPage page = new LoginPage();
        page.login(user, pass);
    }
}
