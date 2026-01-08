package jiraSteps;

import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import jiraPages.CreatingTaskPage;
import jiraPages.ProjectTestPage;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTestSteps {
    private final ProjectTestPage projectPage = new ProjectTestPage();
    private final CreatingTaskPage taskPage = new CreatingTaskPage();
    private int initialCount;
    private int updatedCount;

    @И("пользователь переключается на фильтр 'Все задачи'")
    public void userSwitchesToAllTasksFilter() {
        projectPage.allTasks();
    }

    @Тогда("пользователь обновляет список задач")
    public void userRefreshesTaskList() {
        projectPage.refreshTasks();
    }

    @И("запоминает текущее количество задач")
    public void userRemembersCurrentTaskCount() {
        initialCount = projectPage.getTotalTasksCount();
    }

    @Когда("пользователь создает быструю задачу")
    public void userCreatesQuickTask() {
        String project = CustomProperties.getProps().getProperty("project");
        String subject = CustomProperties.getProps().getProperty("subject");
        taskPage.createQuickTaskSimple(project, subject);
    }

    @И("счетчик задач увеличился на 1")
    public void taskCounterIncreasedByOne() {
        updatedCount = projectPage.getTotalTasksCount();
        assertEquals(initialCount + 1, updatedCount,
                "После создания задачи, общее количество задач не увеличилось");
    }

    @И("пользователь переходит к созданной задаче")
    public void userNavigatesToCreatedTask() {
        projectPage.requestHasBeenCreate();
    }

    @И("пользователь переводит задачу в статус 'Выполнено'")
    public void userConvertsTaskToCompleted() {
        projectPage.convertingTheTaskToCompleted();
    }

    @Если("пользователь ищет задачу TestSeleniumATHomework")
    public void userSearchesTestSeleniumATHomework() {
        String taskATHomework = CustomProperties.getProps().getProperty("taskATHomework");
        projectPage.searchAndSubmit(taskATHomework);
    }
}
