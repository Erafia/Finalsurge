package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import steps.CalendarSteps;
import steps.FullAddWorkoutSteps;
import steps.LoginRegistrationSteps;
import steps.WorkoutPageSteps;
import utils.PropertyManager;

public class BaseTest {
    LoginRegistrationSteps loginRegistrationSteps;
    CalendarSteps calendarSteps;
    FullAddWorkoutSteps fullAddSteps;
    WorkoutPageSteps workoutPageSteps;
    PropertyManager pManager;
    protected String password;
    protected String email;

    @BeforeClass
    public void baseSetupDriver(){
        pManager = PropertyManager.getInstance();
        password = pManager.get("password");
        email  = pManager.get("email");
        Configuration.startMaximized = true;
        Configuration.timeout = 20000;
        Configuration.screenshots = true;
        loginRegistrationSteps = new LoginRegistrationSteps();
        calendarSteps = new CalendarSteps();
        fullAddSteps = new FullAddWorkoutSteps();
        workoutPageSteps = new WorkoutPageSteps();
    }
}
