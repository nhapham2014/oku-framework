package utils;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateUtil {

    public static LocalDate getDateFromDayElement(WebElement day) {
        // remove "Choose "
        String aria = day.getAttribute("aria-label")
                .replace("Choose ", "")
                .replaceAll("(st|nd|rd|th)", "");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);

        return LocalDate.parse(aria, formatter);
    }

    public static long daysFromNow(LocalDate date) {
        return ChronoUnit.DAYS.between(LocalDate.now(), date);
    }
}
