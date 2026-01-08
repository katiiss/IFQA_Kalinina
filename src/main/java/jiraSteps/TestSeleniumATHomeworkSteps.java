package jiraSteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jiraPages.TestSeleniumATHomeworkPage;

public class TestSeleniumATHomeworkSteps {
    private final TestSeleniumATHomeworkPage testSeleniumATHomeworkPage=new TestSeleniumATHomeworkPage();

    @Then("the task status is displayed")
    public void отображается_статус_задачи() {
        testSeleniumATHomeworkPage.getTaskStatus();
    }

    @Then("the fix version is displayed")
    public void отображается_версия_исправления() {
        testSeleniumATHomeworkPage.getFixInVersions();
    }

    @When("ожидается статус задачи {string}")
    public void ожидается_статус_задачи(String expectedStatus) {
        testSeleniumATHomeworkPage.waitForStatus(expectedStatus);
    }
}
