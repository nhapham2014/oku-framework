package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static utils.DateUtil.*;

public class TourDetail extends CommonPage{
    private By byBtnBookNow = By.id("CartOpen");
    private By byBtnAskQuestion = By.xpath("//div[contains(@class,'list-button')][a[@id='CartOpen']]//a[text()='Ask a question']");
    private By byDrnNumberTraveller = By.xpath("//select[@id='demo-customized-select']");
    // tất cả ngày trong calendar
    private By byAllDays = By.xpath("//div[@class='react-datepicker__month']//div[contains(@class,'react-datepicker__day')]");
    // icon next month >
    private By byBtnNextMonth = By.xpath("//button[@aria-label='Next Month']");
    // icon previous month
    private By byBtnPreMonth = By.xpath("//button[@aria-label='Previous Month']");
    // các ngày không được chọn
    private By byOutsideMonth = By.xpath("//div[contains(@class,'react-datepicker__day--outside-month')]");




    public TourDetail(WebDriver driver) {
        super(driver);
    }
    public void clickBtnBookNow(){
        scrollToElement(byBtnBookNow);
        click(byBtnBookNow);
    }
    public void goToNextMonth() {

            click(byBtnNextMonth);

    }
    private List<WebElement> getAvailableDaysInCurrentMonth() {
        return driver.findElements(By.cssSelector(".react-datepicker__day"))
                .stream()
                .filter(day -> {
                    String cls = day.getAttribute("class");
                    return !cls.contains("react-datepicker__day--disabled")
                            && !cls.contains("react-datepicker__day--outside-month");
                })
                .collect(Collectors.toList());
    }
    public LocalDate selectRandomStartDateGte60Days() {
        LocalDate today = LocalDate.now();

        // Nhảy ít nhất 2 tháng để đảm bảo >= 60
        goToNextMonth();
        goToNextMonth();

        for (int i = 0; i < 6; i++) { // max scan 6 tháng
            List<WebElement> days = getAvailableDaysInCurrentMonth();

            List<WebElement> validDays = days.stream()
                    .filter(d -> daysFromNow(
                            getDateFromDayElement(d)) >= 60)
                    .collect(Collectors.toList());

            if (!validDays.isEmpty()) {
                WebElement chosen = validDays.get(
                        new Random().nextInt(validDays.size()));
                chosen.click();
                return getDateFromDayElement(chosen);
            }

            goToNextMonth();
        }

        throw new RuntimeException("No start date >= 60 days found");
    }
    public LocalDate selectRandomStartDateLt60Days() {
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 2; i++) { // chỉ scan tháng hiện tại + tháng sau
            List<WebElement> days = getAvailableDaysInCurrentMonth();

            List<WebElement> validDays = days.stream()
                    .filter(d -> {
                        long diff = ChronoUnit.DAYS.between(
                                today, getDateFromDayElement(d));
                        return diff > 0 && diff < 60;
                    })
                    .collect(Collectors.toList());

            if (!validDays.isEmpty()) {
                WebElement chosen = validDays.get(
                        new Random().nextInt(validDays.size()));
                chosen.click();
                return getDateFromDayElement(chosen);
            }

            goToNextMonth();
        }

        throw new RuntimeException("No start date < 60 days found");
    }




}
