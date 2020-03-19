package elements;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class Input {

    public static void writeText(String id, String text) {
        if (StringUtils.isNotEmpty(text)) {
            log.info("Filling in field with id = " + id + " with text: " + text);
            $(By.id(id)).clear();
            $(By.id(id)).sendKeys(text);
        }
    }
}