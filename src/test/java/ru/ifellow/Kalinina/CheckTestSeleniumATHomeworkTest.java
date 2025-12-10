package ru.ifellow.Kalinina;

import jiraPages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckTestSeleniumATHomeworkTest extends WebHooks {
    private final LoginJiraPage loginPage = new LoginJiraPage();
    private final DashboardPage dashboard = new DashboardPage();
    private final ProjectTestPage projectPage = new ProjectTestPage();
    private final CreatingTaskPage taskPage = new CreatingTaskPage();
    private final TestSeleniumATHomeworkPage task = new TestSeleniumATHomeworkPage();

    private final String project = "Test (TEST)";
    private final String subject = "testikk";
    private final String status = "СДЕЛАТЬ";
    private final String version = "Version 2.0";

    private final String username = CustomProperties.getProps().getProperty("username");
    private final String password = CustomProperties.getProps().getProperty("password");

    @Test
    @DisplayName("Проверка в задаче TestSeleniumATHomework статус задачи и версию")
    void testCheckingStatusAndVersion() {
        loginPage.loginWithConfigCredentials(username, password);
        dashboard.navigateToTestProject();

        projectPage.allTasks();
        projectPage.refreshTasks();

        int initialCount = projectPage.getTotalTasksCount();
        taskPage.createQuickTaskSimple(project, subject);
        projectPage.refreshTasks();

        projectPage.allTasks();

        int updatedCount = projectPage.getTotalTasksCount();
        assertEquals(initialCount + 1, updatedCount);

        assertEquals(status, task.getTaskStatus());
        assertTrue(task.getFixInVersions().contains(version));
    }
}
