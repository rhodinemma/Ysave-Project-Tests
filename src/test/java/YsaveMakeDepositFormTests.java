import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import drivers.DriverSingleton;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.YsaveDepositForm;
import pages.YsaveDepositHome;
import pages.YsaveLoginPage;
import utils.Constants;
import utils.FrameworkProperties;
import utils.Log;

import static org.junit.Assert.assertEquals;

public class YsaveMakeDepositFormTests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static YsaveLoginPage ysaveLoginPage;
    static YsaveDepositHome ysaveDepositHome;
    static YsaveDepositForm ysaveDepositForm;
    static ExtentTest test;
    static ExtentReports report = new ExtentReports("report/YsaveDepositsFormReport.html");

    @BeforeClass
    public static void initializeObjects(){
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance(frameworkProperties.getProperty(Constants.BROWSER));
        driver = DriverSingleton.getDriver();
        ysaveLoginPage = new YsaveLoginPage();
        ysaveDepositHome = new YsaveDepositHome();
        ysaveDepositForm = new YsaveDepositForm();
        test = report.startTest("Ysave Deposit Form");
        Log.getLogData(Log.class.getName());
        Log.startTest("Ysave Form Tests");
    }

    @Test
    @DisplayName("Should be validate user")
    public void authenticateUser(){
        driver.get("http://localhost:8080/login/auth");
        // Log.info("Navigating to " + Constants.URL);
        ysaveLoginPage.accessAccount("super", "pass");
        test.log(LogStatus.PASS, "A user can successfully access their account using correct credentials");
    }

    @Test
    @DisplayName("Should fill the make deposit form")
    public void fillMakeDepositForm(){
        driver.get("http://localhost:8080/deposit/create");
        ysaveDepositForm.getDepositAmount("10,000");
        ysaveDepositForm.getCurrency(1);
        ysaveDepositForm.getDepositType(1);
        ysaveDepositForm.getDepositTo(3);
        ysaveDepositForm.getDepositDay(4);
        ysaveDepositForm.getDepositMonth(8);
        ysaveDepositForm.getDepositYear(0);
        ysaveDepositForm.submitForm();
        assertEquals("Date of Deposit should be within 60 days of date reported", ysaveDepositForm.depositDistributionError());
        test.log(LogStatus.PASS, "User is able to enter the required form details");
    }

    @AfterClass
    public static void closeObjects(){
        report.endTest(test);
        report.flush();
        driver.close();
    }
}
