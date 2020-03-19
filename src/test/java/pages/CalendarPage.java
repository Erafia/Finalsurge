package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import models.Workout;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CalendarPage extends BasePage {
    private static final String CALENDAR_URL = DASHBOARD_URL + "Calendar.cshtml";
    private static final String CALENDAR_ID = "CalendarContent";
    private String DATE_XPATH = "//td[@data-day = %s and @data-month = %s and @data-year = %s]";
    private String DATE_HOVER_XPATH = "//td[@align = 'center']";
    private static final String OPEN_DROPDOWN_ON_DATE_CSS = ".calendar-add.dropdown";
    private String WORKOUT_XPATH = "//div[text() = '%s']";
    private String WORKOUT_CSS = ".fc-event-activity";

    public CalendarPage openPage() {
        log.info("Opening Calendar page of the application by url: " + CALENDAR_URL);
        open(CALENDAR_URL);
        try {
            isPageOpened();
        } catch (NoSuchElementException e) {
            log.error("Page is not opened: element 'Calendar' is not found.");
            Assert.fail("Calendar page cannot be opened.");
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
        $(By.xpath(DATE_XPATH + DATE_HOVER_XPATH)).hover()
                .find(OPEN_DROPDOWN_ON_DATE_CSS).click();
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
     * This method is used to validate presence or not presence of a workout with particular Activity type on a certain date
     *
     * @param workout - date is taken from the workout
     * @param shouldBePresent - set false in case the workout should not be added to the calendar
     */
    public CalendarPage checkWorkoutAddedToCalendar(Workout workout, boolean shouldBePresent) {
        log.debug("Check a workout was added to the date " + workout.getDate() + " and Activity type '" + workout.getActivityType() + "'");
        List<Integer> dateItems = splitDate(workout.getDate());
        int month = dateItems.get(0);
        int day = dateItems.get(1);
        int year = dateItems.get(2);
        DATE_XPATH = String.format(DATE_XPATH, day, month, year);
        WORKOUT_XPATH = String.format(WORKOUT_XPATH, workout.getActivityType());
        if (shouldBePresent) {
            $(By.xpath(DATE_XPATH + WORKOUT_XPATH)).shouldBe(Condition.visible);
        } else {
            $(By.xpath(DATE_XPATH + WORKOUT_XPATH)).shouldNotBe(Condition.visible);
        }
        return this;
    }

    public int getNumberOfTrainingsOnDate(int day, int month, int year){
        log.info("Checking number of workouts created for date: " + month + "/" + day + "/" + year);
        DATE_XPATH = String.format(DATE_XPATH, day, month, year);
        List<SelenideElement> workouts = $(By.xpath(DATE_XPATH)).findAll(WORKOUT_CSS);
        log.info("The number of workouts is: " + workouts.size());
        return workouts.size();
    }
    public int getNumberOfTrainingsOnDate(int day, int month){
        Calendar now = Calendar.getInstance();
        return getNumberOfTrainingsOnDate(day, month, now.get(Calendar.YEAR));
    }

    public int getNumberOfTrainingsOnDate(int day){
        Calendar now = Calendar.getInstance();
        return getNumberOfTrainingsOnDate(day, now.get(Calendar.MONTH) + 1, now.get(Calendar.YEAR));
    }

    public static List<Integer> splitDate(String date) {
        String[] dateValues = date.split("/");
        List<Integer> intValues = Arrays.stream(dateValues)
                .map(dateValue -> Integer.parseInt(dateValue))
                .collect(Collectors.toList());
        return intValues;
    }
}
