package steps;

import io.qameta.allure.Step;
import models.Workout;
import pages.FullAddPage;

public class FullAddWorkoutSteps {
    FullAddPage fullAddPage;

    public FullAddWorkoutSteps(){
        fullAddPage = new FullAddPage();
    }

    @Step("Select Activity type = {activityType} with subtype = {activitySubtype}")
    public FullAddWorkoutSteps selectWorkoutTypeSubtype(String activityType, String activitySubtype){
        fullAddPage.selectActivity(activityType, activitySubtype);
        return this;
    }

    @Step("Fill in the full add workout form with data and save the workout")
    public FullAddWorkoutSteps submitFullAddForm(Workout workout){
        fullAddPage.fillInTheForm(workout)
                .submitForm();
        return this;
    }


}
