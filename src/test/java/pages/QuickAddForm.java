package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import models.QuickAddWorkout;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static elements.Checkbox.updateCheckbox;
import static elements.Dropdown.selectOption;
import static elements.Input.writeText;

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
        Assert.fail("Quick add form did not open.");
    }

    public QuickAddForm fillInTheForm(QuickAddWorkout workoutModel) {
        writeText(DATE_FIELD_ID, workoutModel.getDate());
        writeText(TIME_FIELD_ID, workoutModel.getTimeOfDay());
        writeText(NAME_FIELD_ID, workoutModel.getWorkoutName());
        writeText(DESCRIPTION_FIELD_ID, workoutModel.getWorkoutDescription());
        writeText(DISTANCE_FIELD_ID, workoutModel.getDistance());
        writeText(DURATION_FIELD_ID, workoutModel.getDuration());
        writeText(PACE_FIELD_ID, workoutModel.getPace());
        writeText(NOTES_FIELD_ID, workoutModel.getNotes());
        selectOption(ACTIVITY_TYPE_DROPDOWN_ID, workoutModel.getActivityType());
        selectOption(DISTANCE_TYPE_DROPDOWN_ID, workoutModel.getDistanceUnit());
        selectOption(PACE_TYPE_DROPDOWN_ID, workoutModel.getPaceUnit());
        selectOption(HOW_FEEL_DROPDOWN_ID, workoutModel.getHowIFelt());
        selectOption(PERCEIVED_EFFORT_DROPDOWN_ID, workoutModel.getPerceivedEffort());
        updateCheckbox(PLANNED_CHECKBOX_ID, workoutModel.getShowPlannedDistanceDuration());
        updateCheckbox(IS_RACE_CHECKBOX_ID, workoutModel.getMarkAsRace());
        updateCheckbox(SAVE_TO_LIBRARY_CHECKBOX_ID, workoutModel.getSaveToLibrary());
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
