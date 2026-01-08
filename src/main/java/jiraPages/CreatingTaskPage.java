package jiraPages;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Data
public class CreatingTaskPage {
    private final SelenideElement createBugButton = $x("//a[@id='create_link']").as("Кнопка: Создание задачи");
    private final SelenideElement projectField = $x("//input[@id='project-field']").as("Поле выбора проекта для задачи");
    private final SelenideElement issueTypeField = $x("//input[@id='issuetype-field']").as("Поле для выбора типа задачи");
    private final SelenideElement subjectField = $x("//input[@class='text long-field']").as("Поле для написания темы задачи");
    private final SelenideElement descriptionField = $x("//div[@id='description-wiki-edit']//iframe").as("Поле для описания задачи");
    private final SelenideElement visualDescriptionButton = $x("//div[@id='description-wiki-edit']//li[@data-mode='wysiwyg']/button").as("Визуальный при описании задачи");
    private final SelenideElement fixVersionsButton = $x("//select[@id='fixVersions']//option[@value='10000']").as("Кнопка исправить в версиях на Version 1.0");
    private final SelenideElement priorityButton = $x("//input[@id='priority-field']").as("Кнопка для выбора приоритета для задачи");
    private final SelenideElement labelField = $x("//textarea[@id='labels-textarea']").as("Поле для ввода метки для задачи");
    private final SelenideElement environmentField = $x("//div[@id='environment-wiki-edit']//iframe").as("Поле для окружения задачи");
    private final SelenideElement visualEnvironmentButton = $x("//div[@id='environment-wiki-edit']//li[@data-mode='wysiwyg']/button").as("Визуальный при окружении задачи");
    private final SelenideElement affectedVersionsButton = $x("//select[@id='versions']//option[@value='10000']").as("Кнопка заторнуты версии: Version 1.0");
    private final SelenideElement relatedTasksButton = $x("//select[@id='issuelinks-linktype']/option[@value='clones']").as("Связанные задачи: clones");
    private final SelenideElement taskField = $x("//div[@id='issuelinks-issues-multi-select']/textarea").as("Задача");
    private final SelenideElement executorButton = $x("//button[@id='assign-to-me-trigger']").as("Исполнитель: назначить меня");
    private final SelenideElement linkEpicField = $x("//input[@id='customfield_10100-field']").as("Ссылка на эпик");
    private final SelenideElement sprintField = $x("//input[@id='customfield_10104-field']").as("Выбор спринта");
    private final SelenideElement severityList = $x("//select[@id='customfield_10400']/option [@value='10101']").as("Выбор серьезности");
    private final SelenideElement creatingBugWithDescription = $x("//input[@id='create-issue-submit']").as("Кнопка создание после записи описания бага");
    private final SelenideElement visualEditorBody = $x("//body").as("Тело визуального редактора");

    private static final String TYPE = "Ошибка";

    public void createBug(String project, String subject, String description,
                          String priority, String labels, String environment,
                          String tasks, String epicLink, String sprint) {
        startBugCreation();
        fillProjectAndTypeAndTopic(project,subject);
        fillDescription(description);
        fixVersions();
        choosePriority(priority);
        selectLabel(labels);
        fillEnvironment(environment);
        selectAffectedVersions();
        selectRelatedTasksAndTask(tasks);
        choosePerformer();
        chooseLinkEpic(epicLink);
        chooseSprint(sprint);
        chooseSeverity();
        finalBugCreation();
    }

    public void startBugCreation() {
        createBugButton.click();
    }

    public void fillProjectAndTypeAndTopic(String project, String subject) {
        projectField.shouldBe(visible, Duration.ofSeconds(10))
                .setValue(project)
                .press(Keys.TAB);
        issueTypeField.shouldBe(visible, Duration.ofSeconds(10))
                .setValue(TYPE)
                .press(Keys.ENTER);
        subjectField.setValue(subject);
    }

    public void fillDescription(String description) {
        if (!"true".equals(visualDescriptionButton.getAttribute("aria-pressed"))) {
            visualDescriptionButton.shouldBe(visible, Duration.ofSeconds(10)).click();
        }
        switchTo().frame(descriptionField);
        visualEditorBody.setValue(description);
        switchTo().defaultContent();
    }

    public void fixVersions() {
        fixVersionsButton.click();
    }

    public void choosePriority(String priority) {
        priorityButton.shouldBe(visible, Duration.ofSeconds(10))
                .setValue(priority)
                .press(Keys.ENTER);
    }

    public void selectLabel(String labels) {
        labelField.setValue(labels)
                .press(Keys.ENTER);
    }

    public void fillEnvironment(String environment) {
        if (!"true".equals(visualEnvironmentButton.getAttribute("aria-pressed"))) {
            visualEnvironmentButton.shouldBe(visible, Duration.ofSeconds(10)).click();
        }
        switchTo().frame(environmentField);
        visualEditorBody.setValue(environment);
        switchTo().defaultContent();
    }

    public void selectAffectedVersions() {
        affectedVersionsButton.click();
    }

    public void selectRelatedTasksAndTask(String tasks) {
        relatedTasksButton.click();
        taskField.setValue(tasks)
                .press(Keys.TAB);
    }

    public void choosePerformer() {
        executorButton.click();
    }

    public void chooseLinkEpic(String epicLink) {
        linkEpicField.sendKeys(epicLink + Keys.DOWN + Keys.ENTER);
    }

    public void chooseSprint(String sprint) {
        sprintField.sendKeys(sprint + Keys.DOWN + Keys.ENTER);
    }

    public void chooseSeverity() {
        severityList.click();
    }

    public void finalBugCreation() {
        creatingBugWithDescription.click();
    }

    public void createQuickTaskSimple(String project, String subject) {
        startBugCreation();
        fillProjectAndTypeAndTopic(project,subject);
        finalBugCreation();
    }
}
