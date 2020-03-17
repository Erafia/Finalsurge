package elements;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
@Log4j2

public class Checkbox {

    public static void updateCheckbox(String id, String text) {
        if (StringUtils.isNotEmpty(text)) {
            $(By.id(id)).setSelected(Boolean.parseBoolean(text));
            log.info("Setting checkbox with id " + id + " " + text);
        }
    }
}