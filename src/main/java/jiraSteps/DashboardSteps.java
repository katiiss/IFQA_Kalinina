package jiraSteps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import jiraPages.DashboardPage;
import jiraPages.ProjectTestPage;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardSteps {
    private final DashboardPage dashboardPage = new DashboardPage();
    private final ProjectTestPage projectTestPage = dashboardPage.navigateToTestProject();

    @Когда("пользователь переходит в проект Test")
    public void userNavigatesToTestProject() {
        dashboardPage.navigateToTestProject();
    }

    @Тогда("отображается надпись 'Открытые задачи'")
    public void openTasksTextIsDisplayed() {
        String checkingTheTest2 = CustomProperties.getProps().getProperty("checkingTheTest2");
        String actualText = projectTestPage.getOpenTasksText();
        assertEquals(checkingTheTest2, actualText, "После перехода в проект не отображается надпись 'Открытые задачи'");
    }
}
