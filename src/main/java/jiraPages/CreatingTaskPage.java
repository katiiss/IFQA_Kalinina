package jiraPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class CreatingTaskPage {
    private final SelenideElement createBugButton =$x("//a[@id='create_link']").as("Кнопка: Создание задачи");
    private final SelenideElement projectField =$x("//input[@id='project-field']").as("Поле выбора проекта для задачи");
    private final SelenideElement issueTypeField =$x("//input[@id='issuetype-field']").as("Поле для выбора типа задачи");
    private final SelenideElement subjectField =$x("//input[@class='text long-field']").as("Поле для написания темы задачи");
    private final SelenideElement descriptionField =$x("//div[@id='description-wiki-edit']//iframe").as("Поле для описания задачи");
    private final SelenideElement visualDescriptionButton =$x("//div[@id='description-wiki-edit']//li[@data-mode='wysiwyg']/button").as("Визуальный при описании задачи");
    private final SelenideElement fixVersionsButton =$x("//select[@id='fixVersions']//option[@value='10000']").as("Кнопка исправить в версиях на Version 1.0");
    private final SelenideElement priorityButton =$x("//input[@id='priority-field']").as("Кнопка для выбора приоритета для задачи");
    private final SelenideElement labelField =$x("//textarea[@id='labels-textarea']").as("Поле для ввода метки для задачи");
    private final SelenideElement environmentField =$x("//div[@id='environment-wiki-edit']//iframe").as("Поле для окружения задачи");
    private final SelenideElement visualEnvironmentButton =$x("//div[@id='environment-wiki-edit']//li[@data-mode='wysiwyg']/button").as("Визуальный при окружении задачи");
    private final SelenideElement affectedVersionsButton =$x("//select[@id='versions']//option[@value='10000']").as("Кнопка заторнуты версии: Version 1.0");
    private final SelenideElement relatedTasksButton =$x("//select[@id='issuelinks-linktype']/option[@value='clones']").as("Связанные задачи: clones");
    private final SelenideElement taskField =$x("//span[text()='TEST-209979']").as("Задача: TEST-209979");
    private final SelenideElement executorButton =$x("//button[@id='assign-to-me-trigger']").as("Исполнитель: назначить меня");
    private final SelenideElement linkEpicField =$x("//input[@id='customfield_10100-field']").as("Ссылка на эпик: 879");
    private final SelenideElement sprintField =$x("//input[@id='customfield_10104-field']").as("Выбор спринта");
    private final SelenideElement severityList =$x("//select[@id='customfield_10400']/option [@value='10101']").as("Выбор серьезности");
    private final SelenideElement creatingBugWithDescription =$x("//input[@id='create-issue-submit']").as("Кнопка создание после записи описания бага");

    public void openCreateTaskForm() {
        createBugButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Метод для выбора проекта
    public void selectProject(String projectName) {
        projectField
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(projectName)
                .press(Keys.ENTER);
    }

    // Метод для выбора типа задачи
    public void selectIssueType(String issueType) {
        issueTypeField
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(issueType)
                .press(Keys.ENTER);
    }

    // Метод для заполнения темы задачи
    public void setSubject(String subject) {
        subjectField
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(subject);
    }

    // Метод для заполнения описания в визуальном редакторе
    public void setDescription(String description) {
        // Переключаемся на визуальный режим
        visualDescriptionButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();

        // Переключаемся на iframe и заполняем описание
        switchTo().frame(descriptionField);
        Selenide.$("body").setValue(description);
        switchTo().defaultContent();
    }

    // Метод для выбора фикс-версии
    public void selectFixVersion() {
        fixVersionsButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Метод для выбора приоритета
    public void selectPriority(String priority) {
        priorityButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(priority)
                .press(Keys.ENTER);
    }

    // Метод для добавления меток
    public void addLabels(String labels) {
        labelField
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(labels);
    }

    // Метод для заполнения окружения
    public void setEnvironment(String environment) {
        // Переключаемся на визуальный режим
        visualEnvironmentButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();

        // Переключаемся на iframe и заполняем окружение
        switchTo().frame(environmentField);
        Selenide.$("body").setValue(environment);
        switchTo().defaultContent();
    }

    // Метод для выбора затронутых версий
    public void selectAffectedVersions() {
        affectedVersionsButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Метод для выбора типа связанных задач
    public void selectRelatedTasksType() {
        relatedTasksButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Метод для выбора конкретной задачи
    public void selectTask() {
        taskField
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Метод для назначения себя исполнителем
    public void assignToMe() {
        executorButton
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Метод для указания ссылки на эпик
    public void setEpicLink(String epicNumber) {
        linkEpicField
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(epicNumber);
    }

    // Метод для выбора спринта
    public void selectSprint(String sprint) {
        sprintField
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(sprint)
                .press(Keys.ENTER);
    }

    // Метод для выбора серьезности
    public void selectSeverity() {
        severityList
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Метод для создания задачи
    public void submitTask() {
        creatingBugWithDescription
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
    }

    // Комбинированный метод для быстрого создания бага
    public void createBug(String project, String issueType, String subject, String description) {
        openCreateTaskForm();
        selectProject(project);
        selectIssueType(issueType);
        setSubject(subject);
        setDescription(description);
        submitTask();
    }

    public void createQuickTaskSimple(String project, String issueType, String subject) {
        openCreateTaskForm();
        selectProject(project);
        selectIssueType(issueType);
        setSubject(subject);
        submitTask();
    }

    }
