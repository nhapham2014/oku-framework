package pages;

import base.BasePage;
import components.TopBarNavigation;
import org.openqa.selenium.WebDriver;

public class CommonPage extends BasePage {

    protected TopBarNavigation topBarNavigation;

    public CommonPage(WebDriver driver) {
        super(driver);
        System.out.println("CommonPage driver = " + driver);
        topBarNavigation = new TopBarNavigation(driver);
    }

    public void navigateToNorthernJapan() {
        topBarNavigation.navigateToNorthernJapan();
    }

    public void navigateToWhoWeAre() {
        topBarNavigation.navigateToWhoWeAre();
    }
    public void navigateToResponsibleTourism() {
        topBarNavigation.navigateToResponsibleTourism();
    }
    public void navigateToOurKumanoKudo() {
        topBarNavigation.navigateToOurKumanoKudo();
    }
    public void navigateToOurNakasendoBranch() {
        topBarNavigation.navigateToOurNakasendoBranch();
    }
    public void navigateToOurGuests() {
        topBarNavigation.navigateToOurGuests();
    }
    public void navigateToContactByAboutUs() {
        topBarNavigation.navigateToContactByAboutUs();
    }
    //Useful information section
    public void navigateToFAQ() {
        topBarNavigation.navigateToFAQ();
    }
    public void navigateToPracticalTourInformation() {
        topBarNavigation.navigateToPracticalTourInformation();
    }
    public void navigateToTourDifficulty() {
        topBarNavigation.navigateToTourDifficulty();
    }
    public void navigateToLuggage() {
        topBarNavigation.navigateToLuggage();
    }
    public void navigateToJapaneseAccommodations() {
        topBarNavigation.navigateToJapaneseAccommodations();
    }
    public void navigateToOnsenHotSprings() {
        topBarNavigation.navigateToOnsenHotSprings();
    }
    public void navigateToJapaneseFoodDietaryRequirements() {
        topBarNavigation.navigateToJapaneseFoodDietaryRequirements();
    }
    //Insight section
    public void navigateToInsight() {
        topBarNavigation.navigateToInsight();
    }
    //Contact section
    public void navigateToContact() {
        topBarNavigation.navigateToContact();
    }

}
