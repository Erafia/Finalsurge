package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import models.Workout;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static elements.Dropdown.selectOption;

@Log4j2
public class QuickAddForm extends FullAddPage {
    private static final String QUICK_ADD_FORM_HEADER_ID = "WorkoutAddHeader";
    private final String ACTIVITY_TYPE_DROPDOWN_ID = "ActivityType";
    protected final String CANCEL_BUTTON_ID = "CancelClose";
    protected final String HOW_FEEL_DROPDOWN_ID = "HowFeel";


    QuickAddForm openPage() {
        return this;
    }

    void isPageOpened() {
        log.debug("Checking the 'WORKOUT QUICK ADD' form is opened.");
        try {
            $(By.id(QUICK_ADD_FORM_HEADER_ID)).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("Form is not opened: header element is not found.");
            Assert.fail("Quick add form cannot be opened.");
        }
    }


    /**
     * One should distinguish the mood radio button and how I felt dropdown when generating a workout model
     * for quick add form a .howIFeltDropdown should be used when creating a model
     */
    public QuickAddForm fillInTheForm(Workout workoutModel) {
        super.fillInTheForm(workoutModel);
        selectOption(ACTIVITY_TYPE_DROPDOWN_ID, workoutModel.getActivityType());
        selectOption(HOW_FEEL_DROPDOWN_ID, workoutModel.getHowIFeltDropdown());
        return this;
    }

    public QuickAddForm submitForm() {
        log.info("Submitting 'Quick Add' form");
        super.submitForm();
        return this;
    }

    public void closeFormWithoutSaving() {
        $(By.id(CANCEL_BUTTON_ID)).click();
    }
}
