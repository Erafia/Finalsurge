package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.LoginPage;

@Log4j2
public class LoginRegistrationSteps {
    LoginPage loginPage;


    public LoginRegistrationSteps(){
        loginPage = new LoginPage();
    }

    @Step ("Login using email: {email} and password: {password}.")
    public LoginRegistrationSteps login(String email, String password){
        loginPage
                .openPage()
                .logIn(email, password);
        return this;
    }
}
