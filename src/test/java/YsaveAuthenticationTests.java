import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.YsaveDepositHome;
import pages.YsaveLoginPage;
import utils.Constants;
import utils.FrameworkProperties;
import utils.Log;

import static org.junit.Assert.assertEquals;

public class YsaveAuthenticationTests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static YsaveLoginPage ysaveLoginPage;
    static YsaveDepositHome ysaveDepositHome;
    static ExtentTest test;
    static ExtentReports report = new ExtentReports("report/YsaveReport.html");

    @BeforeClass
    public static void initializeObjects(){
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        ysaveLoginPage = new YsaveLoginPage();
        ysaveDepositHome = new YsaveDepositHome();
        test = report.startTest("Ysave Tests");
        Log.getLogData(Log.class.getName());
        Log.startTest("Ysave Authentication Tests");
    }

    @Test
    @DisplayName("Should give a user access to their account")
    public void allowLogin(){
        driver.get("http://localhost:8080/login/auth");
        // Log.info("Navigating to " + Constants.URL);
        ysaveLoginPage.accessAccount("super", "pass");
        assertEquals("Manage Deposits", ysaveDepositHome.getHomepageTitle());
        test.log(LogStatus.PASS, "A user can successfully access their account using correct credentials");
    }

    @Test
    @DisplayName("Should deny a user access to their account")
    public void denyLogin() {
        driver.get("http://localhost:8080/login/auth");
        ysaveLoginPage.accessAccount("rhodin", "wrong");
        String check = ysaveLoginPage.loginError();
        System.out.println(check);
        assertEquals("Sorry, we were not able to find a user with that username and password.", ysaveLoginPage.loginError());
        test.log(LogStatus.PASS, "A user is denied access if they enter wrong credentials");
    }

    @AfterClass
    public static void closeObjects(){
        
        report.endTest(test);
        report.flush();
        driver.close();
    }
}
