package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class Tour extends CommonPage {


    public Tour(WebDriver driver) {
        super(driver);
    }
    public void clickViewDetailTourButton(String tourLink){
        By byBtnViewDetail = By.xpath("//a[@href='"+tourLink+"']/button");
    }
}
