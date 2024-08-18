package com.project_two_functional_tests.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.project_two_functional_tests.pages.DashboardPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@Disabled("Temporarily disabled")
public class DashboardSteps {

    private WebDriver driver;
    private DashboardPage dashboardPage;

    @Before("@dashboard-statistics")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        this.dashboardPage = new DashboardPage(driver);
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
        this.dashboardPage.dashboardFiguresLoaded();
    }

    @Then("I should see the {string} with a value of {string}")
    public void iShouldSeeTitleWithAValueOfValue(String title, String value ) {
        assertTrue(this.dashboardPage.iShouldSeeCard(title, value));
    }

}
