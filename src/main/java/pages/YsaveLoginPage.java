package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YsaveLoginPage {
    private WebDriver driver;

    public YsaveLoginPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id ="username")
    private WebElement usernameField;

    @FindBy(id ="password")
    private WebElement passwordField;

    @FindBy(css = "#submit")
    private WebElement loginClick;

    @FindBy(css = "#page-content-wrapper > div:nth-child(2) > div")
    private WebElement loginFailureMessage;

    public void accessAccount(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginClick);
    }

    public String loginError(){ return loginFailureMessage.getText(); }

}
