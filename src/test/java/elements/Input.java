package elements;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

@Log4j2
public class Input {

    public static void writeText(String id, String text) {
        if (StringUtils.isNotEmpty(text)) {
            log.info("Filling in field with id = " + id + " with text: " + text);
            $(By.id(id)).clear();
            $(By.id(id)).sendKeys(text);
        }
    }

    public static void compareText(String id, String text) {
        if (StringUtils.isNotEmpty(text)) {
            log.info("Comparing text in field with id = " + id + " with text: " + text);
            assertEquals($(By.id(id)).getText(), text, "Texts do not match \n " +
                    "Actual: " + $(By.id(id)).getText() +
                    "\nExpected: " + text);
        }
    }
}