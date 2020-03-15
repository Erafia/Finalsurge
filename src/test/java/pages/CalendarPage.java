package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import models.QuickAddWorkout;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CalendarPage extends BasePage {
    private static final String CALENDAR_URL = "https://log.finalsurge.com/Calendar.cshtml";
    private static final String CALENDAR_ID = "CalendarContent";
    private String DATE_XPATH = "//td[@data-day = %s and @data-month = %s and @data-year = %s]//td[@align = 'center']";
    private static final String OPEN_DROPDOWN_ON_DATE_CLASS = ".calendar-add.dropdown";
    private String WORKOUT_XPATH = "/../../../../..//div[text() = '%s']";

    public CalendarPage openPage() {
        log.info("Opening Calendar page of the application by url: " + CALENDAR_URL);
        open(CALENDAR_URL);
        try {
            isPageOpened();
        } catch (NoSuchElementException e) {
            log.error("Page is not opened: element 'Calendar' is not found.");
        }
        return this;
    }

    public void isPageOpened() {
        log.debug("Checking the Calendar page is opened.");
        $(By.id(CALENDAR_ID)).shouldBe(Condition.visible);
    }

    /**
     * The @param date should contain any number of the values of overloaded method:
     * - day available only (int day)
     * - day and month are available (int day, int month)
     * - the whole date (int day, int month, int year)
     * Day parameter is obligatory.
     * In case any parameter is omitted, the default values are the current month, year.
     */
    public CalendarPage selectOptionFromDropDownOnDate(int day, int month, int year, String option) {
        log.info("Select '" + option + "' option for the date: " + month + "/" + day + "/" + year);
        DATE_XPATH = String.format(DATE_XPATH, day, month, year);
        $(By.xpath(DATE_XPATH)).hover()
                .find(OPEN_DROPDOWN_ON_DATE_CLASS).click();
        $(By.partialLinkText(option)).click();
        return this;
    }

    public CalendarPage selectOptionFromDropDownOnDate(int day, int month, String option) {
        Calendar now = Calendar.getInstance();
        return selectOptionFromDropDownOnDate(day, month, now.get(Calendar.YEAR), option);
    }

    public CalendarPage selectOptionFromDropDownOnDate(int day, String option) {
        Calendar now = Calendar.getInstance();
        return selectOptionFromDropDownOnDate(day, now.get(Calendar.MONTH) + 1, now.get(Calendar.YEAR), option);
    }

    /**
     * This method is used to validate presence or not presence of a workout on a certain date
     *
     * @param quickAddWorkout - date is taken from the workout
     * @param shouldBePresent - set false in case the workout should not be added to the calendar
     */
    public CalendarPage checkWorkoutAddedToCalendar(QuickAddWorkout quickAddWorkout, boolean shouldBePresent) {
        log.info("Check a workout was added to the date " + quickAddWorkout.getDate() + " and Activity type '" +quickAddWorkout.getActivityType() + "'");
        List<Integer> dateItems = splitDate(quickAddWorkout.getDate());
        int month = dateItems.get(0);
        int day = dateItems.get(1);
        int year = dateItems.get(2);
        DATE_XPATH = String.format(DATE_XPATH, day, month, year);
        WORKOUT_XPATH = String.format(WORKOUT_XPATH, quickAddWorkout.getActivityType());
        if (shouldBePresent) {
            $(By.xpath(DATE_XPATH + WORKOUT_XPATH)).shouldBe(Condition.visible);
        } else {
            $(By.xpath(DATE_XPATH + WORKOUT_XPATH)).shouldNotBe(Condition.visible);
        }
        return this;
    }

    public static List<Integer> splitDate(String date) {
        String[] dateValues = date.split("/");
        List<Integer> intValues = Arrays.stream(dateValues)
                .map(dateValue -> Integer.parseInt(dateValue))
                .collect(Collectors.toList());
        return intValues;
    }
}
