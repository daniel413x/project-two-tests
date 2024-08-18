package com.project_two_functional_tests.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.project_two_functional_tests.pages.WarehousesPage;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WarehouseSteps {

    private WebDriver driver;
    private WarehousesPage warehousesPage;

    @Before("@warehouses or @warehouse-creation or @warehouse-update or @warehouse-delete")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        this.warehousesPage = new WarehousesPage(driver);
    }

    @After("@warehouses or @warehouse-creation or @warehouse-update or @warehouse-delete")
    public void after() {
        if (this.driver != null) {
          this.driver.quit();
          ResetDatabase.run();
        }
    }

    @Given("I am on the warehouses page")
    public void iAmOnTheWarehousesPage() {
        this.warehousesPage.get();
    }

    @When("the warehouses load")
    public void theWarehouseCardsLoaded() {
        this.warehousesPage.warehouseCardsLoaded();
    }

    @Then("I should see rows of warehouses")
    public void iShouldSeeTheWarehouses() {
        assertTrue(this.warehousesPage.containsWarehouseWithName("CA1"));
    }

    @And("I have opened the create warehouse form modal")
    public void iHaveOpenedTheCreateWarehouseFormModal() {
        this.warehousesPage.clickOnCreateWarehouseButton();
    }

    @When("I enter valid input for a new warehouse")
    public void iEnterValidInput() {
        this.warehousesPage.enterFormInputs("DCTest1", "1000", "4000 Connecticut Avenue NW", "Washington", "District of Columbia", "20009");
    }

    @And("I press the warehouse form submit button")
    public void iPressTheSubmitButton() {
        this.warehousesPage.clickOnModalSubmitButton();
    }

    @Then("the warehouse form submission should be successful")
    public void theFormSubmissionShouldBeSuccessful() {
        assertTrue(this.warehousesPage.containsWarehouseWithName("DCTest1"));
    }

    @And("the created warehouse should appear in the list of warehouses")
    public void theCreatedProductCategoryShouldAppearInTheListOfCategories() {
        assertTrue(this.warehousesPage.containsWarehouseWithName("DCTest1"));
    }
}
