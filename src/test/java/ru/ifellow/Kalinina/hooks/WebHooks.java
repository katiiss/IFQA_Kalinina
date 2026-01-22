package ru.ifellow.Kalinina.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import utils.CustomProperties;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeAll
    public static void loadConfig() {
        CustomProperties.loadProperties();
    }
    public static void setupAllureSelenide() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                        .screenshots(Boolean.parseBoolean(CustomProperties.getProps().getProperty("allure.screenshots")))
                        .savePageSource(Boolean.parseBoolean(CustomProperties.getProps().getProperty("allure.savePageSource")))
                        .includeSelenideSteps(Boolean.parseBoolean(CustomProperties.getProps().getProperty("allure.includeSelenideSteps"))));
    }

    @BeforeEach
    public void initBrowser() {
        String chromeDriverPath = CustomProperties.getProps().getProperty("chrome.driver.path");
        String driverVersion = CustomProperties.getProps().getProperty("driver.version");

        if (chromeDriverPath != null && !chromeDriverPath.trim().isEmpty()) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath.trim());
        }
        else if (driverVersion != null && !driverVersion.trim().isEmpty()) {
            Configuration.browserVersion = driverVersion.trim();
        }
        Configuration.browser = CustomProperties.getProps().getProperty("browser");
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = Integer.parseInt(CustomProperties.getProps().getProperty("timeout"));

        open(CustomProperties.getProps().getProperty("base.url"));
        getWebDriver().manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        Selenide.closeWebDriver();
    }
}

