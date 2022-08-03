package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YsaveDepositHome {
    private WebDriver driver;

    public YsaveDepositHome(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="#page-content-wrapper > div:nth-child(4) > div > div.panel.panel-heading.ysheading")
    private WebElement homepageTitle;

    public String getHomepageTitle(){
        return homepageTitle.getText();
    }
}
