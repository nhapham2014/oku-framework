package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
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
    By byValidDay = By.cssSelector(
            ".react-datepicker__day" +
                    ":not(.react-datepicker__day--disabled)" +
                    ":not(.react-datepicker__day--outside-month)"
    );
    private By byCalendar = By.xpath("//div[@class='react-datepicker']");
    private By byCurrentMonth = By.xpath("//div[@class='react-datepicker__current-month']");
    private By byBtnNextMonth = By.xpath("//div[@class='react-datepicker']//button[@aria-label='Next Month']");
    // icon previous month
    private By byBtnPreMonth = By.xpath("//button[@aria-label='Previous Month']");
    private By byBtnContinueBook = By.id("CheckoutInitiate");
    private By byBtnAsk = By.xpath("//div[button[@id='CheckoutInitiate']]//a[@id='EnquiryTourPage1']");
    private By byListRadio = By.xpath("//div[@role='radiogroup']");
    private By byTravellerSelectWrapper = By.xpath("//select[@id='demo-customized-select']/parent::div");


    public TourDetail(WebDriver driver) {
        super(driver);
    }
    public void clickBtnBookNow(){
        WebElement btnBook = waitUtil.waitForPresenceOfElementLocated(byBtnBookNow);
        scrollToElement(btnBook);
        click(byBtnBookNow);
    }
    public void openDatePicker() {

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(byCalendar)
        );

        jsClick(input);

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".react-datepicker")
                )
        );
    }

    public void goToNextMonth() {
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".react-datepicker")
                )
        );

        String currentMonthText = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector(".react-datepicker__current-month")
                )
        ).getText();

        WebElement nextBtn = wait.until(
                ExpectedConditions.elementToBeClickable(byBtnNextMonth)
        );

        jsClick(nextBtn);

        // đợi month đổi
        wait.until(d -> {
            try {
                return !d.findElement(
                        By.cssSelector(".react-datepicker__current-month")
                ).getText().equals(currentMonthText);
            } catch (Exception e) {
                return false;
            }
        });

        wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(byValidDay)
        );
    }
    public void clickNextMonth(int times) {

        // đảm bảo datepicker đang mở
        wait.until(ExpectedConditions.presenceOfElementLocated(byCalendar));

        for (int i = 0; i < times; i++) {

            WebElement monthEl = wait.until(
                    ExpectedConditions.presenceOfElementLocated(byCurrentMonth)
            );

            String currentMonthText = monthEl.getText();

            WebElement nextBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(byBtnNextMonth)
            );

            jsClick(nextBtn);

            // đợi tháng đổi (React re-render)
            wait.until(driver -> {
                try {
                    String newMonth =
                            driver.findElement(byCurrentMonth).getText();
                    return !newMonth.equals(currentMonthText);
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            });

            // đợi ngày render xong
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byValidDay));
        }
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
        Random random = new Random();

        // nhảy tối thiểu 2 tháng
        clickNextMonth(3);

        for (int i = 0; i < 6; i++) {

            List<WebElement> days = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(byValidDay)
            );

            List<WebElement> validDays = days.stream()
                    .filter(d -> {
                        try {
                            return daysFromNow(getDateFromDayElement(d)) >= 60;
                        } catch (Exception e) {
                            return false;
                        }
                    })
                    .toList();

            if (!validDays.isEmpty()) {
                WebElement chosen = validDays.get(
                        random.nextInt(validDays.size())
                );
                jsClick(chosen);
                return getDateFromDayElement(chosen);
            }

            goToNextMonth();
        }

        throw new RuntimeException("No start date >= 60 days found");
    }
    public LocalDate selectRandomStartDateLt60Days() {
        LocalDate today = LocalDate.now();
        Random random = new Random();

        for (int monthScan = 0; monthScan < 2; monthScan++) {

            List<WebElement> days = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(byValidDay)
            );

            List<WebElement> validDays = days.stream()
                    .filter(day -> {
                        try {
                            LocalDate date = getDateFromDayElement(day);
                            long diff = ChronoUnit.DAYS.between(today, date);
                            return diff > 0 && diff < 60;
                        } catch (Exception e) {
                            return false; // tránh element lỗi aria-label
                        }
                    })
                    .toList();

            if (!validDays.isEmpty()) {
                WebElement chosen = validDays.get(random.nextInt(validDays.size()));
                jsClick(chosen);
                return getDateFromDayElement(chosen);
            }

            goToNextMonth();
        }

        throw new RuntimeException("No start date < 60 days found");
    }
    public void clickContinueBook(){
        waitUtil.waitForPresenceOfElementLocated(byBtnContinueBook);

        scrollToElement(driver.findElement(byBtnContinueBook));

        jsClick(driver.findElement(byBtnContinueBook));
    }

    public void waitForSingleSupplementToAppear(int travellers) {

        if (travellers <= 1 || travellers % 2 == 0) {
            return; // không cần radio
        }

        wait.until(driver ->
                !driver.findElements(byListRadio).isEmpty()
        );

    }


    public void selectSingleSupplement(String opt, int travellers) {

        if (travellers <= 1 || travellers % 2 == 0) {
            System.out.println("Single supplement not applicable");
            return;
        }

        waitForSingleSupplementToAppear(travellers);

        By byLabel = By.xpath(
                "//input[@name='selectSingle' and @value='" +
                        (opt.equalsIgnoreCase("Yes") ? "true" : "false") +
                        "']/ancestor::label"
        );

        WebElement label = wait.until(
                ExpectedConditions.presenceOfElementLocated(byLabel)
        );

        scrollToElement(label);
        jsClick(label);
    }


    public String selectNumberOfTravellers2(String number) {

        WebElement select = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("demo-customized-select")
                )
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("""
        const select = arguments[0];
        const value = arguments[1];

        select.value = value;
        select.dispatchEvent(new Event('input', { bubbles: true }));
        select.dispatchEvent(new Event('change', { bubbles: true }));
        select.dispatchEvent(new Event('blur', { bubbles: true }));
    """, select, number);

        // ✅ đợi state React cập nhật xong
        wait.until(d ->
                number.equals(select.getAttribute("value"))
        );

        // ✅ return giá trị thực tế đang được chọn
        return select.getAttribute("value");
    }
    public void waitForRadioGroupIfNeeded(int traveller) {

        By radioGroup = By.cssSelector("div[role='radiogroup']");

        if (traveller > 1 && traveller % 2 == 1) {
            // ✅ radio PHẢI xuất hiện
            wait.until(ExpectedConditions.presenceOfElementLocated(radioGroup));
        } else {
            // ✅ radio KHÔNG tồn tại trong DOM
            wait.until(ExpectedConditions.numberOfElementsToBe(radioGroup, 0));
        }
    }
    public void bookTourWithNoRoomOptionAndNoDeposit(String number){
        clickBtnBookNow();
        selectNumberOfTravellers2(number);
        selectRandomStartDateLt60Days();
        clickContinueBook();
    }
    public void bookTourWithNoRoomOptionAndHasDeposit(String number){
        clickBtnBookNow();
        selectNumberOfTravellers2(number);
        selectRandomStartDateGte60Days();
        clickContinueBook();
    }
    public void bookTourWithHasRoomOptAndNoDeposit(String number, String singleSuppliment){
        clickBtnBookNow();
        selectNumberOfTravellers2(number);
        waitForRadioGroupIfNeeded(Integer.parseInt(number));
        selectRandomStartDateLt60Days();
        selectSingleSupplement(singleSuppliment, Integer.parseInt(number));
        clickContinueBook();
    }
    public void bookTourWithHasRoomOptAndHasDeposit(String number, String singleSuppliment){
        clickBtnBookNow();
        selectNumberOfTravellers2(number);
        waitForRadioGroupIfNeeded(Integer.parseInt(number));
        selectRandomStartDateGte60Days();
        selectSingleSupplement(singleSuppliment, Integer.parseInt(number));
        clickContinueBook();
    }







}
