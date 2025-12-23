package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Tour extends CommonPage {


    public Tour(WebDriver driver) {
        super(driver);
    }
    public void clickViewDetailTourButton(String tourLink){
        By byBtnViewDetail = By.xpath("//a[@href='"+tourLink+"']/button");
        scrollToElement(byBtnViewDetail);
//        click(byBtnViewDetail);
        WebElement trip = wait.until(
                ExpectedConditions.elementToBeClickable(byBtnViewDetail)
        );
        trip.click();
    }
}
