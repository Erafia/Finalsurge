package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

@Log4j2
public class LoginTests extends BaseTest {

    @Test (description = "Check it is possible to login to the application using valid credentials")
    public void loginUsingValidCredentials(){
        loginRegistrationSteps.login(email, password);
    }
}
