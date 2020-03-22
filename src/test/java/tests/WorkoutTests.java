package tests;

import lombok.extern.log4j.Log4j2;
import models.Workout;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class WorkoutTests extends BaseTest{
    Workout workout;

    @BeforeClass
    public void createWorkout(){
        workout = Workout.builder()
                .activityType("Swim")
                .date("3/5/2020")
                .timeOfDay("5:45 AM")
                .workoutName("Chasing smoke on the water")
                .workoutDescription("Trying explain why does this workout start sooo early")
                .showPlannedDistanceDuration("true")
                .distance("500.22")
                .distanceUnit("m")
                .duration("1:15:00")
                .markAsRace("false")
                .pace("14:59")
                .paceUnit("min/100m")
                .perceivedEffort("6 (Moderate)")
                .moodRadioButton("Good")
                .caloriesBurnt("42")
                .build();
    }

    @BeforeMethod
    public void loginUsingValidCredentials() {
        loginRegistrationSteps.login(email, password);
    }

    @Test
    public void checkTraining(){
        calendarSteps.openFullAddPage(5);
        fullAddSteps.selectWorkoutTypeSubtype("Swim", "No Sub-Type")
                .submitFullAddForm(workout);
        calendarSteps.selectActionOnWorkout(workout,"View");
        workoutPageSteps.compare(workout);
        calendarSteps.selectActionOnWorkout(workout,"Delete");
        calendarSteps.confirmAlert()
                     .verifyWorkoutRemoved(workout);
    }
}
