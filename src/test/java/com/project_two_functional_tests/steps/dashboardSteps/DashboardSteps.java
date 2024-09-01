package com.project_two_functional_tests.steps.dashboardSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.project_two_functional_tests.pages.DashboardPage;
import com.project_two_functional_tests.utils.HeadlessChromeDriver;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardSteps {

    private WebDriver driver;
    private DashboardPage dashboardPage;

    @BeforeAll
    public static void resetDatabaseBeforeAll() {
        ResetDatabase.run();
    }

    @Before("@dashboard-statistics")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.dashboardPage = new DashboardPage(driver);
        driver.manage().window().maximize();
    }

    @After("@dashboard-statistics")
    public void after() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Given("I am on the dashboard page")
    public void iAmOnTheDashboardPage() {
        this.dashboardPage.get();
    }

    @When("the dashboard loads")
    public void theDashboardLoads() {
        this.dashboardPage.dashboardSectionLoaded();
    }

    /**
     * 01-dashboard-statistics.feature
     * Feature: Dashboard Statistics
     */

    @Then("I should see the {string} with a value of {string}")
    public void iShouldSeeTitleWithAValueOfValue(String title, String value) {
        assertTrue(this.dashboardPage.iShouldSeeCard(title, value));
    }

}
