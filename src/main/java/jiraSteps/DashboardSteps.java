package jiraSteps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import jiraPages.DashboardPage;


public class DashboardSteps {
    private final DashboardPage dashboardPage = new DashboardPage();

    @When("пользователь переходит в проект Test")
    public void пользователь_переходит_в_проект_Test() {
        dashboardPage.navigateToTestProject();
    }

    @Then("отображается лента активности на дашборде")
    public void отображается_лента_активности_на_дашборде() {
        dashboardPage.getActivityFeedText();
    }
}
