package ru.ifellow.Kalinina;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import utils.CustomProperties;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {

    @BeforeAll
    public static void loadConfig() {
        CustomProperties.loadProperties();
    }

    @BeforeEach
    public void initBrowser() {

        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;
        Selenide.open(CustomProperties.getProps().getProperty("base.url"));
        getWebDriver().manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        Selenide.closeWebDriver();
    }
}

