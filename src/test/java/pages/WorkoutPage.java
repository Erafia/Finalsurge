package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import models.Workout;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

@Log4j2
public class WorkoutPage extends BasePage {
    protected final static  String WORKOUT_BOX_ID = "PublicBox";
    protected final static  String UPDATE_WORKOUT_BUTTON_ID = "WorkoutEditLink";
    protected final static  String ACTIVITY_TYPE_SUBTYPE_CSS = ".activityTypeName";
    protected final static  String WORKOUT_NAME_XPATH = "//span[@class = 'activityTypeName']/../following-sibling::div";
    protected final static  String DATE_TIME_XPATH = "//span[@class = 'activityTypeName']/../preceding-sibling::div/small";
    protected final static  String WORKOUT_DESCRIPTION_XPATH = ".testme.dont-break-out";
    protected final static  String DISTANCE_DURATION_CSS = ".label.label-info";
    protected final static  String PACE_XPATH = "//small[text() = 'Workout Statistics:']/following-sibling::span[@class = 'label label']";
    protected final static  String HOW_I_FELT_XPATH = "//small[text() = 'How I Felt:']/following-sibling::span";

    /**
     * Cannot be used as workout`s id is used in page adress and is generated dynamically
     */
    BasePage openPage() {
        return this;
    }

    void isPageOpened() {
        log.debug("Checking the Workout page is opened.");
        $(By.id(WORKOUT_BOX_ID)).shouldBe(Condition.visible);
    }

    public void compareWorkoutInfo(Workout workout){
        log.info("Starting to compare the given workout with actual display on workout page...");
        compareElements(workout.getActivityType(), $(ACTIVITY_TYPE_SUBTYPE_CSS).getText());
        compareElements(workout.getWorkoutName(), $(By.xpath(WORKOUT_NAME_XPATH)).getText());
        compareElements("Workout Description:\n" + workout.getWorkoutDescription(), $(WORKOUT_DESCRIPTION_XPATH).getText());
        String [] statsValues = stringProcessor($(DISTANCE_DURATION_CSS).getText());
        compareElements(workout.getDistance(), statsValues[0]);
        compareElements(workout.getDistanceUnit(), statsValues[1]);
        compareElements(workout.getDuration(), statsValues[3]);
        String [] paceValues = stringProcessor($(By.xpath(PACE_XPATH)).getText());
        compareElements(workout.getPace(), paceValues[0]);
        compareElements(workout.getPaceUnit(), paceValues[1]);
        String [] dateValues = parseDate($(By.xpath(DATE_TIME_XPATH)).getText());
        compareElements(workout.getDate(), dateValues[0]);
        compareElements(workout.getTimeOfDay(), dateValues[1]);
        compareElements(workout.getHowIFeltDropdown(), $(By.xpath(HOW_I_FELT_XPATH)).getText());
        compareElements(workout.getMoodRadioButton(), $(By.xpath(HOW_I_FELT_XPATH)).getText());
    }

    public void compareElements(String s1, String s2) {
        if (StringUtils.isNotEmpty(s1)) {
            assertEquals(s1, s2, "Values do not match \n " +
                    "Actual: " + s1 +
                    "\nExpected: " + s2);
        }
    }

    public String[] parseDate(String date){
        String[] dateValues = date.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
        LocalDate lDate = LocalDate.parse(dateValues[0], formatter);
        formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        dateValues[0] = lDate.format(formatter);
        return dateValues;
    }

    public String[] stringProcessor(String stats, String delimiter){
        String[] statsValues = stats.split(delimiter);
        return statsValues;
    }

    public String[] stringProcessor(String stats){
        return stringProcessor(stats, " ");
    }

}
