package hooks;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.qameta.allure.selenide.AllureSelenide;
import utils.CustomProperties;
import utils.JsonEdit;

public class Hooks {

    @BeforeAll
    public static void loadConfig() {
        CustomProperties.loadProperties();
        JsonEdit.getOriginalAuthJson();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(Boolean.parseBoolean(CustomProperties.getProps().getProperty("allure.screenshots")))
                .savePageSource(Boolean.parseBoolean(CustomProperties.getProps().getProperty("allure.savePageSource")))
                .includeSelenideSteps(Boolean.parseBoolean(CustomProperties.getProps().getProperty("allure.includeSelenideSteps"))));
    }

    @AfterAll
    public static void restoreCredentials() {
        JsonEdit.restoreOriginalAuthJson();
    }
}