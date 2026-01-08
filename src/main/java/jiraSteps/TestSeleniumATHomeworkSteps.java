package jiraSteps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import jiraPages.TestSeleniumATHomeworkPage;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSeleniumATHomeworkSteps {
    private final TestSeleniumATHomeworkPage testSeleniumATHomeworkPage = new TestSeleniumATHomeworkPage();

    @Тогда("пользователь ожидает статус задачи")
    public void userWaitsForTaskStatus() {
        String expectedStatus = CustomProperties.getProps().getProperty("status");
        testSeleniumATHomeworkPage.waitForStatus(expectedStatus);
    }

    @И("статус задачи соответствует ожидаемому")
    public void taskStatusMatchesExpected() {
        String expectedStatus = CustomProperties.getProps().getProperty("status");
        String actualStatus = testSeleniumATHomeworkPage.getTaskStatus();
        assertEquals(expectedStatus, actualStatus, "Статус задачи не " + expectedStatus + ". Фактический статус: " + actualStatus);
    }

    @И("версия в задаче соответствует ожидаемой")
    public void fixInVersionsMatchesExpected() {
        String expectedVersion = CustomProperties.getProps().getProperty("version");
        String actualVersion = testSeleniumATHomeworkPage.getFixInVersions();
        assertEquals(expectedVersion, actualVersion,
                "Исправить в версиях не " + expectedVersion + ". Фактическая версия: " + actualVersion);
    }

    @Тогда("проверяются статус и версия задачи")
    public void checkStatusAndVersion() {
        String expectedStatus = CustomProperties.getProps().getProperty("status");
        String expectedVersion = CustomProperties.getProps().getProperty("version");

        String actualStatus = testSeleniumATHomeworkPage.getTaskStatus();
        String actualVersion = testSeleniumATHomeworkPage.getFixInVersions();

        assertEquals(expectedStatus, actualStatus, "Статус задачи не " + expectedStatus + ". Фактический статус: " + actualStatus);
        assertEquals(expectedVersion, actualVersion,
                "Исправить в версиях не " + expectedVersion + ". Фактическая версия: " + actualVersion);
    }
}
