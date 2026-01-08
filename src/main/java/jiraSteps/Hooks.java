package jiraSteps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.PageLoadStrategy;
import utils.CustomProperties;

import static com.codeborne.selenide.Selenide.open;

public class Hooks {

    @BeforeAll
    public static void loadConfig() {
        CustomProperties.loadProperties();
    }

    @Before
    public void initBrowser() {
        CustomProperties.loadProperties();

        System.setProperty("webdriver.chrome.driver", CustomProperties.getProps().getProperty("chromeDriverPath"));
        Configuration.browser = CustomProperties.getProps().getProperty("browser");
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = Integer.parseInt(CustomProperties.getProps().getProperty("timeout"));

        open(CustomProperties.getProps().getProperty("base.url"));

        Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
    }

    @After
    public void teardown() {
        Selenide.closeWebDriver();
    }
}
