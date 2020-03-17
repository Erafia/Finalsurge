package elements;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Log4j2

public class Dropdown {

    public static void selectOption(String id, String text) {
        if (StringUtils.isNotEmpty(text)) {
            $(By.id(id)).selectOption(text);
            log.info("Selecting  '" + text + "' option from dropdown with id = " + id);
        }
    }
}