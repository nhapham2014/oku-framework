package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static utils.DateUtil.*;

public class CheckOut extends CommonPage {
    //Your details section
    private By byTxtFname = By.name("firstName");
    private By byTxtLname = By.name("lastName");
    private By byTxtEmail = By.xpath("//input[@name='email']");
    private By byTxtConfEmail = By.name("emailConfirmation");
    private By byDrpPhoneCountry = By.xpath("//select[@class='PhoneInputCountrySelect']");
    private By byTxtPhone = By.xpath("//input[@class='PhoneInputInput']");
    private By byTxtAddress = By.xpath("//input[@name='address']");
    private By byTxtCity = By.xpath("//input[@name='city']");
    private By byTxtPostcode = By.xpath("//input[@name='postalCode']");
    private By byDrpCountry = By.name("rcrs-country");
    private By byBtnNext1 = By.xpath("//button[@id='Checkout1']");
    private By byBtnBack1 = By.xpath("//button[span[text()='Back']]");
    //Traveller details
    private By byTxtFirstName = By.name("first_name");
    private By byTxtLastName = By.name("last_name");
    private By byDrpGender = By.id("mui-component-select-gender");
    private By byOptMale = By.xpath("//li[text()='Male']");
    private By byOptFemale = By.xpath("//li[text()='Female']");
    private By byTxtBirthday = By.xpath("//input[@placeholder='Date of birth']");
    private By byDrpMonthOfBirth = By.xpath("//select[@class='react-datepicker__month-select']");
    private By byDrpYearOfBirth = By.xpath("//select[@class='react-datepicker__year-select']");
    private By byRadioYes = By.xpath("//input[@value='1']");
    private By byRadioNo = By.xpath("//input[@value='0']");
    private By byDrpDiet = By.xpath("//div[contains(text(),'Please choose')]");
    private By byTxtNote = By.xpath("//textarea[@name='note']");



















    public CheckOut(WebDriver driver) {
        super(driver);
    }
}
