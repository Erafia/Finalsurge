package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import models.QuickAddWorkout;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class QuickAddForm extends BasePage {
    private static final String QUICK_ADD_FORM_HEADER_ID = "WorkoutAddHeader";
    private final String DATE_FIELD_ID = "WorkoutDate";
    private final String TIME_FIELD_ID = "WorkoutTime";
    private final String ACTIVITY_TYPE_DROPDOWN_ID = "ActivityType";
    private final String NAME_FIELD_ID = "Name";
    private final String DESCRIPTION_FIELD_ID = "Desc";
    private final String PLANNED_CHECKBOX_ID = "PlannedWorkout";
    private final String DISTANCE_FIELD_ID = "Distance";
    private final String DISTANCE_TYPE_DROPDOWN_ID = "DistType";
    private final String DURATION_FIELD_ID = "Duration";
    private final String PACE_FIELD_ID = "Pace";
    private final String PACE_TYPE_DROPDOWN_ID = "PaceType";
    private final String HOW_FEEL_DROPDOWN_ID = "HowFeel";
    private final String PERCEIVED_EFFORT_DROPDOWN_ID = "PerEffort";
    private final String NOTES_FIELD_ID = "PostDesc";
    private final String IS_RACE_CHECKBOX_ID = "IsRace";
    private final String SAVE_TO_LIBRARY_CHECKBOX_ID = "SaveLibrary";
    private final String SAVE_BUTTON_ID = "saveButton";
    private final String CANCEL_BUTTON_ID = "CancelClose";

    QuickAddForm openPage() {
        return this;
    }

    void isPageOpened() {
        log.debug("Checking the 'WORKOUT QUICK ADD' form is opened.");
        $(By.id(QUICK_ADD_FORM_HEADER_ID)).shouldBe(Condition.visible);
    }

    public QuickAddForm fillInTheForm(QuickAddWorkout workoutModel) {
        if (workoutModel.getDate() != null) {
            $(By.id(DATE_FIELD_ID)).clear();
            $(By.id(DATE_FIELD_ID)).sendKeys(workoutModel.getDate());
        }
        if (workoutModel.getTimeOfDay() != null) {
            $(By.id(TIME_FIELD_ID)).sendKeys(workoutModel.getTimeOfDay());
        }
        if (workoutModel.getActivityType() != null) {
            $(By.id(ACTIVITY_TYPE_DROPDOWN_ID)).selectOption(workoutModel.getActivityType());
        }
        if (workoutModel.getWorkoutName() != null) {
            $(By.id(NAME_FIELD_ID)).sendKeys(workoutModel.getWorkoutName());
        }
        if (workoutModel.getWorkoutDescription() != null) {
            $(By.id(DESCRIPTION_FIELD_ID)).sendKeys(workoutModel.getWorkoutDescription());
        }
        if (workoutModel.getShowPlannedDistanceDuration() != null) {
            $(By.id(PLANNED_CHECKBOX_ID)).setSelected(Boolean.parseBoolean(workoutModel.getShowPlannedDistanceDuration()));
        }
        if (workoutModel.getDistance() != null) {
            $(By.id(DISTANCE_FIELD_ID)).sendKeys(workoutModel.getDistance());
        }
        if (workoutModel.getDistanceUnit() != null) {
            $(By.id(DISTANCE_TYPE_DROPDOWN_ID)).selectOption(workoutModel.getDistanceUnit());
        }
        if (workoutModel.getDuration() != null) {
            $(By.id(DURATION_FIELD_ID)).sendKeys(workoutModel.getDuration());
        }
        if (workoutModel.getPace() != null) {
            $(By.id(PACE_FIELD_ID)).sendKeys(workoutModel.getPace());
        }
        if (workoutModel.getPaceUnit() != null) {
            $(By.id(PACE_TYPE_DROPDOWN_ID)).selectOption(workoutModel.getPaceUnit());
        }
        if (workoutModel.getHowIFelt() != null) {
            $(By.id(HOW_FEEL_DROPDOWN_ID)).selectOption(workoutModel.getHowIFelt());
        }
        if (workoutModel.getPerceivedEffort() != null) {
            $(By.id(PERCEIVED_EFFORT_DROPDOWN_ID)).selectOption(workoutModel.getPerceivedEffort());
        }
        if (workoutModel.getNotes() != null) {
            $(By.id(NOTES_FIELD_ID)).sendKeys(workoutModel.getNotes());
        }
        if (workoutModel.getMarkAsRace() != null) {
            $(By.id(IS_RACE_CHECKBOX_ID)).setSelected(Boolean.parseBoolean(workoutModel.getMarkAsRace()));
        }
        if (workoutModel.getSaveToLibrary() != null) {
            $(By.id(SAVE_TO_LIBRARY_CHECKBOX_ID)).setSelected(Boolean.parseBoolean(workoutModel.getSaveToLibrary()));
        }
        return this;
    }

    public QuickAddForm submitForm() {
        $(By.id(SAVE_BUTTON_ID)).click();
        return this;
    }

    public void closeFormWithoutSaving() {
        $(By.id(CANCEL_BUTTON_ID)).click();
    }
}
