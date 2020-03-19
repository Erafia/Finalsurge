package elements;

import lombok.extern.log4j.Log4j2;
import models.Moods;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class RadioButton {
    protected static String RADIO_XPATH;

    public static void selectRadio(String id, String text) {
        if (StringUtils.isNotEmpty(text)) {
            log.info("Selecting radio button: " + text);
            RADIO_XPATH = String.format(id, text);
            try {
                Moods moods = Moods.valueOf(text.toUpperCase());
                $(By.xpath(RADIO_XPATH)).selectRadio(moods.getMoodValue());
            } catch (IllegalArgumentException e) {
                log.error("The mood defined in the test data does not exist in the application");
            }
        }
    }
}
