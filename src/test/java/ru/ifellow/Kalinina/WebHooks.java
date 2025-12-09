package ru.ifellow.Kalinina;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import utils.CustomProperties;

public class WebHooks {
    @BeforeAll
    public static void loadConfig() {
        CustomProperties.loadProperties();
        Configuration.browserSize = String.valueOf(true);
    }

    @BeforeEach
    public void initBrowser() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
        Selenide.open(CustomProperties.getProps().getProperty("base.url"));
    }

    @AfterEach
    void teardown() {
        Selenide.closeWebDriver();
    }
}

