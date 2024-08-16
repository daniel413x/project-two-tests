package com.project_two_tests.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.project_two_tests.pages.DashboardPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DashboardSteps {

    private WebDriver driver;
    private DashboardPage dashboardPage;

    @Before
    public void before() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        this.driver = new ChromeDriver();
        this.dashboardPage = new DashboardPage(driver);
    }

    @After
    public void after() {
        if(driver != null) {
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

    @Then("I should see the \"Total Items in Inventory\" with a value of 300")
    public void iShouldSeeTheTotalItemsInInventoryWithAValueOf300() {
        assertTrue(this.dashboardPage.iShouldSeeTheTotalItemsInInventoryWithAValueOf300());
    }

    @And("I should see the \"Total Max Capacity\" with a value of 1000")
    public void iShouldSeeTheTotalMaxCapacityWithAValueOf1000() {
        assertTrue(this.dashboardPage.iShouldSeeTheTotalMaxCapacityWithAValueOf1000());
    }
}
