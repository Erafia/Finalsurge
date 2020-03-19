package steps;

import io.qameta.allure.Step;
import models.Workout;
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

    @Step("Open 'Full Add' form for day {day}")
    public CalendarSteps openFullAddPage(int day) {
        calendarPage.openPage()
                .selectOptionFromDropDownOnDate(day, "Full Add");
        return this;
    }

    @Step("Fill in the quick add form with data and save the workout")
    public CalendarSteps submitQuickAddForm(Workout workout){
        quickAddForm.fillInTheForm(workout)
                .submitForm();
        return this;
    }

    @Step ("Check the added workout appeared in the calendar")
    public CalendarSteps checkWorkoutAdded(Workout workout){
        calendarPage.checkWorkoutAddedToCalendar(workout, true);
        return this;
    }

    @Step ("Get number of workouts for day {day}")
    public int getWorkoutsCount(int day){
        return calendarPage.openPage()
                           .getNumberOfTrainingsOnDate(day);
    }


}
