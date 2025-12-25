package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TopBarNavigation extends BasePage {

    private By byLnkTours = By.xpath("//div[text()='Tours']");
    private By byLnkNorthernJapan = By.xpath("//a[@href='/region/northern-japan/']");





    private By byLnkAgent = By.xpath("//a[@href='/about-us/travel-agents/']");
    //About us tab
    private By byLnkAboutUs = By.xpath("//div[text()='About us']");
    private By byLnkWhoWeAre = By.xpath("//a[@href='/about-us/who-we-are/']");
    private By byLnkResponsibleTourism = By.xpath("//a[@href='/about-us/responsible-tourism/']");
    private By byLnkOurKumanoKodoBranch = By.xpath("//a[@href='/about-us/kumano-kodo-branch/']");
    private By byLnkOurNakasendoBranch = By.xpath("//a[@href='/about-us/our-nakasendo-branch/']");
    private By byLnkOurGuests = By.xpath("//a[@href='/about-us/testimonials/']");
    private By byLnkContactOfAboutUs = By.xpath("//a[@href='/about-us/contact-us/']");
    //Useful information tab
    private By byLnkUseful = By.xpath("//div[text()='Useful information ']");
    private By byLnkFAQBeforeBooking = By.xpath("//a[@href='/help/pre-booking-faq/']");
    private By byLnkPracticalTourInformation = By.xpath("//a[@href='/help/practical-info/']");
    private By byLnkTourDifficulty = By.xpath("//a[@href='/help/tour-difficulty/']");
    private By byLnkLuggage = By.xpath("//a[@href='/help/luggage-transfer/']");
    private By byLnkJapaneseAccommodations = By.xpath("//a[@href='/help/accommodation/']");
    private By byLnkOnsenHotSprings = By.xpath("//a[@href='/help/hot-springs/']");
    private By byLnkJapaneseFoodDietaryRequirements = By.xpath("//a[@href='/help/japanese-food/']");
    //Insight tab
    private By byLnkInsight = By.xpath("//div[text()='Insights']");
    //Contact tab
    private By byLnkContact = By.xpath("//div[text()='Contact']");
    //Currency
    private By byLnkCurrency = By.xpath("//div[@id='header-menu']//button[contains(@class,'expanded')]");





    public TopBarNavigation(WebDriver driver) {
        super(driver);
    }
    public void clickTour(){
        click(byLnkTours);
    }

    public void navigateToNorthernJapan() {
        hover(byLnkTours);
        clickbutton(byLnkNorthernJapan);
    }
    //About us section
    public void navigateToWhoWeAre() {
        click(byLnkAboutUs);
        click(byLnkWhoWeAre);
    }
    public void navigateToResponsibleTourism() {
        click(byLnkAboutUs);
        click(byLnkResponsibleTourism);
    }
    public void navigateToOurKumanoKudo() {
        click(byLnkAboutUs);
        click(byLnkOurKumanoKodoBranch);
    }
    public void navigateToOurNakasendoBranch() {
        click(byLnkAboutUs);
        click(byLnkOurNakasendoBranch);
    }
    public void navigateToOurGuests() {
        click(byLnkAboutUs);
        click(byLnkOurGuests);
    }
    public void navigateToContactByAboutUs() {
        click(byLnkAboutUs);
        click(byLnkContactOfAboutUs);
    }
    //Useful information section
    public void navigateToFAQ() {
        click(byLnkUseful);
        click(byLnkFAQBeforeBooking);
    }
    public void navigateToPracticalTourInformation() {
        click(byLnkUseful);
        click(byLnkPracticalTourInformation);
    }
    public void navigateToTourDifficulty() {
        click(byLnkUseful);
        click(byLnkTourDifficulty);
    }
    public void navigateToLuggage() {
        click(byLnkUseful);
        click(byLnkLuggage);
    }
    public void navigateToJapaneseAccommodations() {
        click(byLnkUseful);
        click(byLnkJapaneseAccommodations);
    }
    public void navigateToOnsenHotSprings() {
        click(byLnkUseful);
        click(byLnkOnsenHotSprings);
    }
    public void navigateToJapaneseFoodDietaryRequirements() {
        click(byLnkUseful);
        click(byLnkJapaneseFoodDietaryRequirements);
    }
    //Insight section
    public void navigateToInsight() {
        click(byLnkInsight);
    }
    //Contact section
    public void navigateToContact() {
        click(byLnkContact);
    }

}
