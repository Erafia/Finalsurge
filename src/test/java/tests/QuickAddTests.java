package tests;

import lombok.extern.log4j.Log4j2;
import models.Workout;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class QuickAddTests extends BaseTest {
    Workout workout;
    int initialNumberOfWorkouts;
    int finalNumberOfWorkouts;

    @BeforeClass
    public void createWorkout(){
        workout = Workout.builder()
                .date("3/14/2020")
                .activityType("Run")
                .build();
    }

    @BeforeMethod
    public void loginUsingValidCredentials() {
        loginRegistrationSteps.login(email, password);
    }

    @Test (description = "Check workout added via 'Quick Add' function is displayed in the calendar")
    public void checkWorkoutAddedViaQuickAdd(){
        initialNumberOfWorkouts = calendarSteps.getWorkoutsCount(14);
        calendarSteps.openQuickAddForm(14)
                .submitQuickAddForm(workout)
                .checkWorkoutAdded(workout);
        finalNumberOfWorkouts = calendarSteps.getWorkoutsCount(14);
        assertEquals(initialNumberOfWorkouts + 1,finalNumberOfWorkouts, "Workouts count did not increase.");
    }
}
