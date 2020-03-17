package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import steps.CalendarSteps;
import steps.LoginRegistrationSteps;
import utils.PropertyManager;

public class BaseTest {
    LoginRegistrationSteps loginRegistrationSteps;
    CalendarSteps calendarSteps;
    PropertyManager pManager;
    protected String password;
    protected String email;

    @BeforeClass
    public void setup_Driver(){
        pManager = PropertyManager.getInstance();
        password = pManager.get("password");
        email  = pManager.get("email");
        Configuration.startMaximized = true;
        Configuration.timeout = 20000;
        Configuration.screenshots = true;
        loginRegistrationSteps = new LoginRegistrationSteps();
        calendarSteps = new CalendarSteps();
    }
}
