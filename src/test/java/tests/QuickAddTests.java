package tests;

import lombok.extern.log4j.Log4j2;
import models.QuickAddWorkout;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class QuickAddTests extends BaseTest {
    QuickAddWorkout workout;

    @BeforeClass
    public void createWorkout(){
        workout = QuickAddWorkout.builder()
                .date("3/14/2020")
                .activityType("Run")
                .build();
    }

    @BeforeMethod
    public void loginUsingValidCredentials() {
        loginRegistrationSteps.login(email, password);
    }

    @Test
    public void checkTrainingAddedViaQuickAdd(){
        calendarSteps.openQuickAddForm(14)
                .submitQuickAddForm(workout)
                .checkWorkoutAdded(workout);
    }
}
