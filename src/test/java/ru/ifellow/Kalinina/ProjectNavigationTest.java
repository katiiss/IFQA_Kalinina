package ru.ifellow.Kalinina;

import jiraPages.DashboardPage;
import jiraPages.LoginJiraPage;
import jiraPages.ProjectTestPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectNavigationTest extends WebHooks {
    private final LoginJiraPage loginPage = new LoginJiraPage();
    private final DashboardPage dashboard = new DashboardPage();
    private final ProjectTestPage projectPage = new ProjectTestPage();

    private final String username = CustomProperties.getProps().getProperty("username");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String checkingTheTest = "Лента активности";
    private final String checkingTheTest2 = "Открытые задачи";

    @Test
    @DisplayName("Тест перехода в проект Test")
    void testNavigateToTestProjectPage() {
        loginPage.loginWithConfigCredentials(username, password);
        assertEquals(dashboard.getActivityFeedText(), checkingTheTest, "После авторизации не отображается 'Лента активности");
        dashboard.navigateToTestProject();
        assertEquals(checkingTheTest2, projectPage.getOpenTasksText(), "После перехода в проект не отображается надпись 'Открытые задачи'");
    }
}
