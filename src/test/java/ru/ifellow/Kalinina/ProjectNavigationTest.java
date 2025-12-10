package ru.ifellow.Kalinina;

import jiraPages.DashboardPage;
import jiraPages.LoginJiraPage;
import jiraPages.ProjectTestPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CustomProperties;

public class ProjectNavigationTest extends WebHooks {
    private final LoginJiraPage loginPage = new LoginJiraPage();
    private final DashboardPage dashboard = new DashboardPage();
    private final ProjectTestPage projectPage = new ProjectTestPage();

    private final String username = CustomProperties.getProps().getProperty("username");
    private final String password = CustomProperties.getProps().getProperty("password");

    @Test
    @DisplayName("Тест перехода в проект Test")
    void testNavigateToTestProjectPage() {

        loginPage.loginWithConfigCredentials(username, password);
        dashboard.navigateToTestProject();

    }
}
