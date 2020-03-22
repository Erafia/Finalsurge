package steps;

import io.qameta.allure.Step;
import models.Workout;
import pages.WorkoutPage;

public class WorkoutPageSteps {
    WorkoutPage workoutPage;

    public WorkoutPageSteps(){
        workoutPage = new WorkoutPage();
    }

    @Step ("Compare the given workout with the displayed in the page")
    public WorkoutPageSteps compare(Workout workout){
        workoutPage.compareWorkoutInfo(workout);
        return this;
    }
}
