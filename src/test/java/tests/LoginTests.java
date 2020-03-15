package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.LoginRegistrationSteps;
import utils.PropertyManager;

@Log4j2
public class LoginTests extends BaseTest {
    private String password;
    private String email;

    @BeforeClass
    public void setupDriver(){
        pManager = PropertyManager.getInstance();
        password = pManager.get("password");
        email  = pManager.get("email");
        loginRegistrationSteps = new LoginRegistrationSteps(); //разобраться почему злоебучий BaseTest не отрабатывает
    }
    @Test
    public void loginUsingValidCredentials(){
        loginRegistrationSteps.login(email, password);
    }
}
