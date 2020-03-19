package tests;

import lombok.extern.log4j.Log4j2;
import models.Workout;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class FullAddTests extends BaseTest {
    Workout workout;
    int initialNumberOfWorkouts;
    int finalNumberOfWorkouts;

    @BeforeClass
    public void createWorkout(){
        workout = Workout.builder()
                .date("3/14/2020")
                .timeOfDay("05:45 AM")
                .workoutName("Chasing smoke on the water")
                .workoutDescription("Trying explain why does this workout start sooo early")
                .showPlannedDistanceDuration("true")
                .distance("500")
                .distanceUnit("m")
                .duration("01:15:00")
                .markAsRace("true")
                .perceivedEffort("6 (Moderate)")
                .howIFelt("Good")
                .caloriesBurnt("42")
                .build();
    }

    @BeforeMethod
    public void loginUsingValidCredentials() {
        loginRegistrationSteps.login(email, password);
    }

    @Test
    public void checkTrainingAddedViaFullAdd(){
        initialNumberOfWorkouts = calendarSteps.getWorkoutsCount(14);
        calendarSteps.openFullAddPage(14);
        fullAddSteps.selectWorkoutTypeSubtype("Swim", "Endurance")
                    .submitFullAddForm(workout);
        finalNumberOfWorkouts = calendarSteps.getWorkoutsCount(14);
        assertEquals(initialNumberOfWorkouts + 1,finalNumberOfWorkouts, "Workouts count did not increase.");
    }
}
