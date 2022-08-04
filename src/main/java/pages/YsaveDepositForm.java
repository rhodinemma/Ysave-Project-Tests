package pages;

import drivers.DriverSingleton;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class YsaveDepositForm {
    private WebDriver driver;

    public YsaveDepositForm(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "amountDeposited")
    private WebElement amountDepositField;

    @FindBy(id = "currency")
    private WebElement currencyField;

    @FindBy(id = "natureOfDeposit")
    private WebElement depositTypeField;

    @FindBy(id = "bankDepositedTo")
    private WebElement depositToField;

    @FindBy(id = "dateOfDeposit_day")
    private WebElement dayOfDeposit;

    @FindBy(id = "dateOfDeposit_month")
    private WebElement monthOfDeposit;

    @FindBy(id = "dateOfDeposit_year")
    private WebElement yearOfDeposit;

    @FindBy(id = "create")
    private WebElement submitBtn;

    @FindBy(css = "#page-content-wrapper > div:nth-child(3) > div > div:nth-child(2) > div > div > ul > li > div")
    private WebElement distributionError;

    public String depositDistributionError(){ return distributionError.getText(); }

    public void getDepositAmount(String amount){
        amountDepositField.sendKeys(amount);
    }

    public void getCurrency(int index){
        Select dropdown = new Select(currencyField);
        dropdown.selectByIndex(index);
    }

    public void getDepositType(int index){
        Select secondDropDown = new Select(depositTypeField);
        secondDropDown.selectByIndex(index);
    }

    public void getDepositTo(int index){
        Select thirdDropdown = new Select(depositToField);
        thirdDropdown.selectByIndex(index);
    }

    public void getDepositDay(int index){
        Select fourthDropdown = new Select(dayOfDeposit);
        fourthDropdown.selectByIndex(index);
    }

    public void getDepositMonth(int index){
        Select fifthDropdown = new Select(monthOfDeposit);
        fifthDropdown.selectByIndex(index);
    }

    public void getDepositYear(int index){
        Select sixthDropdown = new Select(yearOfDeposit);
        sixthDropdown.selectByIndex(index);
    }

    public void submitForm(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);
    }
}
