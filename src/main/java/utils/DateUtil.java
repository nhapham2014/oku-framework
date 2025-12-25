package utils;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateUtil {

    public static LocalDate getDateFromDayElement(WebElement day) {
        // VD aria-label:
        // "Choose Satuay, January 17th, 2026"

        String aria = day.getAttribute("aria-label");

        // 1. Bỏ "Choose "
        aria = aria.replace("Choose ", "");

        // 2. Bỏ hậu tố st/nd/rd/th
        aria = aria.replaceAll("(st|nd|rd|th)", "");

        // 3. Bỏ day-of-week (Satuay, / Saturday, ...)
        aria = aria.replaceFirst("^[A-Za-z]+,\\s*", "");

        // Kết quả còn lại:
        // "January 17, 2026"

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

        return LocalDate.parse(aria, formatter);
    }

    public static long daysFromNow(LocalDate date) {
        return ChronoUnit.DAYS.between(LocalDate.now(), date);
    }
}
