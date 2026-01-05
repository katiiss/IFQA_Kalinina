package ru.ifellow.Kalinina;

import jiraPages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IfellowHW3Test extends WebHooks {
    private final LoginJiraPage loginPage = new LoginJiraPage();
    private final DashboardPage dashboard = new DashboardPage();
    private final ProjectTestPage projectPage = new ProjectTestPage();
    private final CreatingTaskPage taskPage = new CreatingTaskPage();
    private final TestSeleniumATHomeworkPage task = new TestSeleniumATHomeworkPage();

    private final String username = CustomProperties.getProps().getProperty("username");
    private final String password = CustomProperties.getProps().getProperty("password");
    private final String checkingTheTest = CustomProperties.getProps().getProperty("checkingTheTest");
    private final String checkingTheTest2 = CustomProperties.getProps().getProperty("checkingTheTest2");
    private final String checkingTheStatus = CustomProperties.getProps().getProperty("checkingTheStatus");

    private final String project = CustomProperties.getProps().getProperty("project");
    private final String subject = CustomProperties.getProps().getProperty("subject");
    private final String description = CustomProperties.getProps().getProperty("description");
    private final String priority = CustomProperties.getProps().getProperty("priority");
    private final String labels = CustomProperties.getProps().getProperty("labels");
    private final String environment = CustomProperties.getProps().getProperty("environment");
    private final String tasks = CustomProperties.getProps().getProperty("tasks");
    private final String epicLink = CustomProperties.getProps().getProperty("epicLink");
    private final String sprint = CustomProperties.getProps().getProperty("sprint");
    private final String status = CustomProperties.getProps().getProperty("status");
    private final String version = CustomProperties.getProps().getProperty("version");
    private final String taskATHomework = CustomProperties.getProps().getProperty("taskATHomework");

    @Test
    @DisplayName("Тест авторизации")
    void successfulLoginTest() {
        loginPage.loginWithConfigCredentials(username, password);
        assertEquals(dashboard.getActivityFeedText(), checkingTheTest, "После авторизации не отображается 'Лента активности");
    }

    @Test
    @DisplayName("Тест перехода в проект Test")
    void testNavigateToTestProjectPage() {
        loginPage.loginWithConfigCredentials(username, password);
        assertEquals(dashboard.getActivityFeedText(), checkingTheTest, "После авторизации не отображается 'Лента активности");
        dashboard.navigateToTestProject();
        assertEquals(checkingTheTest2, projectPage.getOpenTasksText(), "После перехода в проект не отображается надпись 'Открытые задачи'");
    }

    @Test
    @DisplayName("Проверка общего количества заведенных задач в проекте")
    void testTaskCounterWithQuickCreation() {
        loginPage.loginWithConfigCredentials(username, password);
        assertEquals(dashboard.getActivityFeedText(), checkingTheTest, "После авторизации не отображается 'Лента активности");
        dashboard.navigateToTestProject();
        assertEquals(checkingTheTest2, projectPage.getOpenTasksText(), "После перехода в проект не отображается надпись 'Открытые задачи'");
        projectPage.allTasks();
        projectPage.refreshTasks();

        int initialCount = projectPage.getTotalTasksCount();
        taskPage.createQuickTaskSimple(project, subject);
        projectPage.refreshTasks();

        projectPage.allTasks();

        int updatedCount = projectPage.getTotalTasksCount();
        assertEquals(initialCount + 1, updatedCount, "После создания бага, общее количество задач не увеличилось");
    }

    @Test
    @DisplayName("Проверка в задаче TestSeleniumATHomework статус задачи и версию")
    void testCheckingStatusAndVersion() {
        loginPage.loginWithConfigCredentials(username, password);
        assertEquals(dashboard.getActivityFeedText(), checkingTheTest, "После авторизации не отображается 'Лента активности");
        dashboard.navigateToTestProject();
        assertEquals(checkingTheTest2, projectPage.getOpenTasksText(), "После перехода в проект не отображается надпись 'Открытые задачи'");

        projectPage.allTasks();
        projectPage.refreshTasks();

        int initialCount = projectPage.getTotalTasksCount();
        taskPage.createQuickTaskSimple(project, subject);
        projectPage.refreshTasks();

        projectPage.allTasks();

        int updatedCount = projectPage.getTotalTasksCount();
        assertEquals(initialCount + 1, updatedCount, "После создания бага, общее количество задач не увеличилось");

        projectPage.searchAndSubmit(taskATHomework);

        assertTrue(status.equals(task.getTaskStatus()), "Статус задачи не Сделать");
        assertEquals(version, task.getFixInVersions(), "Исправить в версиях не Version 2.0");
    }

    @Test
    @DisplayName("Проверка создания бага и его перевод в выполнено")
    void creatingBugWithDescriptionAndStatusTranslation() {
        loginPage.loginWithConfigCredentials(username, password);
        assertEquals(dashboard.getActivityFeedText(), checkingTheTest, "После авторизации не отображается 'Лента активности");
        dashboard.navigateToTestProject();
        assertEquals(checkingTheTest2, projectPage.getOpenTasksText(), "После перехода в проект не отображается надпись 'Открытые задачи'");

        projectPage.allTasks();
        projectPage.refreshTasks();

        int initialCount = projectPage.getTotalTasksCount();
        taskPage.createQuickTaskSimple(project, subject);
        projectPage.refreshTasks();

        projectPage.allTasks();

        int updatedCount = projectPage.getTotalTasksCount();
        assertEquals(initialCount + 1, updatedCount, "После создания бага, общее количество задач не увеличилось");

        projectPage.searchAndSubmit(taskATHomework);
        assertTrue(status.equals(task.getTaskStatus()), "Статус задачи не Сделать");
        assertEquals(version, task.getFixInVersions(), "Исправить в версиях не Version 2.0");

        taskPage.createBug(project, subject, description, priority, labels, environment, tasks, epicLink, sprint);
        projectPage.requestHasBeenCreate();
        projectPage.convertingTheTaskToCompleted();
        task.waitForStatus(checkingTheStatus);
        assertEquals(checkingTheStatus, task.getTaskStatus(), "Статус задачи не переведен в Готово");
    }
}


