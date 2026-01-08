package jiraSteps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import jiraPages.CreatingTaskPage;
import jiraPages.ProjectTestPage;
import jiraPages.TestSeleniumATHomeworkPage;
import utils.CustomProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatingTaskSteps {
    private final CreatingTaskPage taskPage = new CreatingTaskPage();
    private final ProjectTestPage projectPage = new ProjectTestPage();
    private final TestSeleniumATHomeworkPage testTaskPage = new TestSeleniumATHomeworkPage();

    private int initialCount;
    private int updatedCount;

    @Когда("пользователь создает баг с описанием")
    public void userCreatesBugWithDescription() {
        String project = CustomProperties.getProps().getProperty("project");
        String subject = CustomProperties.getProps().getProperty("subject");
        String description = CustomProperties.getProps().getProperty("description");
        String priority = CustomProperties.getProps().getProperty("priority");
        String labels = CustomProperties.getProps().getProperty("labels");
        String environment = CustomProperties.getProps().getProperty("environment");
        String tasks = CustomProperties.getProps().getProperty("tasks");
        String epicLink = CustomProperties.getProps().getProperty("epicLink");
        String sprint = CustomProperties.getProps().getProperty("sprint");

        taskPage.createBug(project, subject, description, priority, labels,
                environment, tasks, epicLink, sprint);
    }

    @Тогда("статус задачи переводится в 'Готово'")
    public void taskStatusTranslatedToDone() {
        String checkingTheStatus = CustomProperties.getProps().getProperty("checkingTheStatus");
        testTaskPage.waitForStatus(checkingTheStatus);
        String actualStatus = testTaskPage.getTaskStatus();
        assertEquals(checkingTheStatus, actualStatus,
                "Статус задачи не переведен в " + checkingTheStatus);
    }

}
