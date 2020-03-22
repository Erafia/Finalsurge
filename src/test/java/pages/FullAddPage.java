package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import models.Workout;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static elements.Checkbox.updateCheckbox;
import static elements.Dropdown.selectOption;
import static elements.Input.writeText;
import static elements.RadioButton.selectRadio;

@Log4j2
public class FullAddPage extends BasePage{
    protected static final String FULL_ADD_URL = DASHBOARD_URL + "WorkoutAdd.cshtml";
    protected static final String ACTIVITY_TYPE_LIST_ID = "blog_accordion_left";
    protected static String ACTIVITY_TYPE_ID = "//a[contains(text(), '%s') and @data-parent = '#blog_accordion_left']";
    protected static String ACTIVITY_SUBTYPE_ID = "//li//a[contains(text(), '%s')]";
    protected final String DATE_FIELD_ID = "WorkoutDate";
    protected final String TIME_FIELD_ID = "WorkoutTime";
    protected final String NAME_FIELD_ID = "Name";
    protected final String DESCRIPTION_FIELD_ID = "Desc";
    protected final String PLANNED_CHECKBOX_ID = "PlannedWorkout";
    protected final String DISTANCE_FIELD_ID = "Distance";
    protected final String DISTANCE_TYPE_DROPDOWN_ID = "DistType";
    protected final String DURATION_FIELD_ID = "Duration";
    protected final String PACE_FIELD_ID = "Pace";
    protected final String PACE_TYPE_DROPDOWN_ID = "PaceType";
    protected final String PERCEIVED_EFFORT_DROPDOWN_ID = "PerEffort";
    protected final String CALORIES_BURNT_FIELD_ID = "kCal";
    protected final String NOTES_FIELD_ID = "PostDesc";
    protected final String IS_RACE_CHECKBOX_ID = "IsRace";
    protected final String SAVE_TO_LIBRARY_CHECKBOX_ID = "SaveLibrary";
    protected final String SAVE_BUTTON_ID = "saveButton";
    protected final String MOOD_RADIO_BUTTON_XPATH = "//span[contains(text(), '%s')]/preceding-sibling::input";

    FullAddPage openPage() {
        log.info("Opening Full Add Workout page of the application by url: " + FULL_ADD_URL);
        open(FULL_ADD_URL);
        try {
            isPageOpened();
        } catch (NoSuchElementException e) {
            log.error("Page is not opened: element 'Activity Type List' is not found.");
            screenshot("Full_add_page_not_opened");
            Assert.fail("Full Add Workout page cannot be opened.");
        }
        return this;
    }

    void isPageOpened() {
        log.debug("Checking the Full Add Workout page form is opened.");
        $(By.id(ACTIVITY_TYPE_LIST_ID)).shouldBe(Condition.visible);
    }

    public FullAddPage selectActivity(String activityType, String activitySubtype){
        log.info("Select '" + activityType + "' activity type with subtype = '"+ activitySubtype + "'  from the list of activities");
        ACTIVITY_TYPE_ID = String.format(ACTIVITY_TYPE_ID, activityType);
        $(By.xpath(ACTIVITY_TYPE_ID)).click();
        if (!activitySubtype.equals("No Sub-Type")) {
            ACTIVITY_SUBTYPE_ID = String.format(ACTIVITY_SUBTYPE_ID, activitySubtype);
            $(By.xpath(ACTIVITY_SUBTYPE_ID)).click();
        }
        return this;
    }

    /**
     * One should distinguish the mood radio button and how I felt dropdown when generating a workout model
     * for full add form a .moodRadioButton should be used when creating a model
     */
    public FullAddPage fillInTheForm(Workout workoutModel) {
        writeText(DATE_FIELD_ID, workoutModel.getDate());
        writeText(TIME_FIELD_ID, workoutModel.getTimeOfDay());
        writeText(NAME_FIELD_ID, workoutModel.getWorkoutName());
        writeText(DESCRIPTION_FIELD_ID, workoutModel.getWorkoutDescription());
        writeText(DISTANCE_FIELD_ID, workoutModel.getDistance());
        writeText(DURATION_FIELD_ID, workoutModel.getDuration());
        writeText(PACE_FIELD_ID, workoutModel.getPace());
        writeText(NOTES_FIELD_ID, workoutModel.getNotes());
        selectOption(DISTANCE_TYPE_DROPDOWN_ID, workoutModel.getDistanceUnit());
        selectOption(PACE_TYPE_DROPDOWN_ID, workoutModel.getPaceUnit());
        selectOption(PERCEIVED_EFFORT_DROPDOWN_ID, workoutModel.getPerceivedEffort());
        updateCheckbox(PLANNED_CHECKBOX_ID, workoutModel.getShowPlannedDistanceDuration());
        updateCheckbox(IS_RACE_CHECKBOX_ID, workoutModel.getMarkAsRace());
        updateCheckbox(SAVE_TO_LIBRARY_CHECKBOX_ID, workoutModel.getSaveToLibrary());
        writeText(CALORIES_BURNT_FIELD_ID, workoutModel.getCaloriesBurnt());
        selectRadio(MOOD_RADIO_BUTTON_XPATH, workoutModel.getMoodRadioButton());
        return this;
    }

    public FullAddPage submitForm() {
        log.info("Submitting 'Full Add' workout");
        $(By.id(SAVE_BUTTON_ID)).click();
        return this;
    }

}
