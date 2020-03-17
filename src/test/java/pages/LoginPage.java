package pages;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage extends BasePage {
    private final static String LOGIN_FORM_ID = "login-validate";
    private final static String LOGIN_BUTTON_CSS = ".btn.btn-beoro-1";
    private final static String EMAIL_FIELD_ID = "login_name";
    private final static String PASSWORD_FIELD_ID = "login_password";

    public LoginPage openPage() {
        log.info("Opening Login page of the application by url: " + DASHBOARD_URL);
        open(DASHBOARD_URL);
        try{
            isPageOpened();
        }
        catch (NoSuchElementException e){
            log.error("Page is not opened: element 'Login button' is not found.");
            Assert.fail("Login page did not open.");
        }
        return this;
    }

    void isPageOpened() {
        log.debug("Checking the Login page is opened.");
        $(By.id(LOGIN_FORM_ID)).find(LOGIN_BUTTON_CSS).shouldBe(Condition.visible);

    }

    void isUserLoggedIn(){
        log.info("Check user logged in: 'Logout' link should be available");
        $(By.partialLinkText("Logout")).shouldBe(Condition.visible, Condition.enabled);
    }

    public void logIn(String email, String password) {
        log.debug("Input value in 'Email' field: " + email);
        $(By.id(EMAIL_FIELD_ID)).setValue(email);
        log.debug("Input value in 'Password' field:  " + password);
        $(By.id(PASSWORD_FIELD_ID)).setValue(password);
        log.debug("Pressing 'Login' button");
        $(By.id(LOGIN_FORM_ID)).find(LOGIN_BUTTON_CSS).click();
        log.info("Submitting login form");
        isUserLoggedIn();
    }
}
