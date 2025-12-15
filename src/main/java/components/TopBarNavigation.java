package components;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopBarNavigation extends BasePage {

    private By byLnkTours = By.xpath("//div[text()='Tours']");
    private By byLnkNorthernJapan = By.xpath("//a[text()='Northern Japan']");
    private By byLnkAboutUs = By.xpath("//div[text()='About us']");
    private By byLnkUseful = By.xpath("//div[text()='Useful information ']");
    private By byLnkInsight = By.xpath("//div[text()='Insights']");
    private By byLnkContact = By.xpath("//div[text()='Contact']");
    private By byLnkCurrency = By.xpath("//div[@id='header-menu']//button[contains(@class,'expanded')]");
    private By byLnkAgent = By.xpath("//a[@href='/about-us/travel-agents/']");


    public TopBarNavigation(WebDriver driver) {
        super(driver);
    }

    public void navigateLoginPage() {
        click(byLnkNorthernJapan);
    }

    public void navigateRegisterPage() {
        click(byLnkTours);
    }
}
