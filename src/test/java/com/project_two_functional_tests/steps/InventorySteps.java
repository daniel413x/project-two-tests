package com.project_two_functional_tests.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.project_two_functional_tests.pages.InventoryPage;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventorySteps {

    private WebDriver driver;
    private InventoryPage inventoryPage;

    @Before("@inventory-creation")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        this.inventoryPage = new InventoryPage(driver);
    }

    @After("@inventory-creation")
    public void after() {
        if (this.driver != null) {
          this.driver.quit();
          ResetDatabase.run();
        }
    }

    @Given("I am on the all inventory page")
    public void iAmOnTheInventoryPage() {
        this.inventoryPage.get();
    }

    @And("I have opened the create inventory form modal")
    public void iHaveOpenedTheCreateInventoryFormModal() {
        this.inventoryPage.clickOnCreateInventoryButton();
    }

    @When("I enter valid input for new inventory")
    public void iEnterValidInput() {
        this.inventoryPage.enterCreationFormInputs("PenguinPro", "Emperor Penguin Shoes", "Really cool shoes", "Climbing Shoes", "CA1", "59.99", "11", "16");
    }

    @And("I press the create inventory form submit button")
    public void iPressTheSubmitButton() {
        this.inventoryPage.clickOnModalSubmitButton();
    }

    @Then("the created inventory should appear in the list of inventory")
    public void theCreatedInventoryShouldAppearInTheListOfCategories() {
        assertTrue(this.inventoryPage.containsInventoryWithName("Emperor Penguin Shoes"));
    }
}
