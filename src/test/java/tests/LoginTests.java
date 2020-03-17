package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

@Log4j2
public class LoginTests extends BaseTest {

    @Test
    public void loginUsingValidCredentials(){
        loginRegistrationSteps.login(email, password);
    }
}
