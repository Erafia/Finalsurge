package steps;

import io.qameta.allure.Step;
import models.QuickAddWorkout;
import pages.CalendarPage;
import pages.QuickAddForm;

public class CalendarSteps {
    CalendarPage calendarPage;
    QuickAddForm quickAddForm;


    public CalendarSteps() {
        calendarPage = new CalendarPage();
        quickAddForm = new QuickAddForm();
    }

    @Step("Open 'Quick Add' form for day {day}")
    public CalendarSteps openQuickAddForm(int day) {
        calendarPage.openPage()
                .selectOptionFromDropDownOnDate(day, "Quick Add");
        return this;
    }

    @Step("Fill in the quick add form with data and save the workout")
    public CalendarSteps submitQuickAddForm(QuickAddWorkout workout){
        quickAddForm.fillInTheForm(workout)
                .submitForm();
        return this;
    }

    @Step ("Check the added workout appeared in the calendar")
    public CalendarSteps checkWorkoutAdded(QuickAddWorkout workout){
        calendarPage.checkWorkoutAddedToCalendar(workout, true);
        return this;
    }


}
