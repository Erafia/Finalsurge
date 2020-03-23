package tests;

import lombok.extern.log4j.Log4j2;
import models.Workout;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CalendarTests extends BaseTest {
    Workout workout;
    Workout workout2;
    int initialNumberOfWorkoutsDayFrom;
    int initialNumberOfWorkoutsDayTo;
    int finalNumberOfWorkoutsDayFrom;
    int finalNumberOfWorkoutsDayTo;



    @BeforeMethod
    public void loginUsingValidCredentials() {
        loginRegistrationSteps.login(email, password);
    }
    @AfterMethod
    public void logout() {
        loginRegistrationSteps.logout();
    }

    @Test
    public void generateTestData() {
        workout = Workout.builder()
                .date("3/5/2020")
                .activityType("Run")
                .build();
        workout2 = Workout.builder()
                .date("3/6/2020")
                .activityType("Run")
                .build();
        for (int i = 0; i < 2; i++) {
            calendarSteps.openQuickAddForm(5)
                    .submitQuickAddForm(workout);
            calendarSteps.openQuickAddForm(6)
                    .submitQuickAddForm(workout2);
        }
    }

    @Test(description = "Check workouts can be moved to another day")
    public void checkWorkoutsCanBeMoved(){
        initialNumberOfWorkoutsDayFrom = calendarSteps.getWorkoutsCount(5);
        initialNumberOfWorkoutsDayTo = calendarSteps.getWorkoutsCount(10);
        calendarSteps.moveWorkoutsToSelectedDay(5,10);
        finalNumberOfWorkoutsDayFrom = calendarSteps.getWorkoutsCount(5);
        finalNumberOfWorkoutsDayTo = calendarSteps.getWorkoutsCount(10);
        assertEquals(finalNumberOfWorkoutsDayFrom,0, "Workouts were not removed from the original day.");
        assertEquals(finalNumberOfWorkoutsDayTo,initialNumberOfWorkoutsDayTo  + initialNumberOfWorkoutsDayFrom, "Workouts were not moved to the target day.");
    }

    @Test(description = "Check workouts can be copied to another day")
    public void checkWorkoutsCanBeCopied(){
        initialNumberOfWorkoutsDayFrom = calendarSteps.getWorkoutsCount(6);
        initialNumberOfWorkoutsDayTo = calendarSteps.getWorkoutsCount(11);
        calendarSteps.copyWorkoutsToSelectedDay(6,11);
        finalNumberOfWorkoutsDayFrom = calendarSteps.getWorkoutsCount(6);
        finalNumberOfWorkoutsDayTo = calendarSteps.getWorkoutsCount(11);
        assertEquals(finalNumberOfWorkoutsDayFrom,initialNumberOfWorkoutsDayFrom, "Number of workout on the original day has changed.");
        assertEquals(finalNumberOfWorkoutsDayTo,initialNumberOfWorkoutsDayTo + initialNumberOfWorkoutsDayFrom, "Workouts were not copied to the target day^ the total number did not change.");
    }
}
