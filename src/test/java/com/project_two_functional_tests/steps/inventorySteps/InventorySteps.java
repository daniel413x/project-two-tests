package com.project_two_functional_tests.steps.inventorySteps;

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

    @Before("@inventory-list or @inventory-creation or @inventory-sort or @inventory-search or @inventory-update")
    public void before() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        this.inventoryPage = new InventoryPage(driver);
    }

    @After("@inventory-list or @inventory-creation or @inventory-sort or @inventory-search or @inventory-update")
    public void after() {
        if (this.driver != null) {
            this.driver.quit();
            ResetDatabase.run();
        }
    }

    @Given("I am on the {string} inventory page")
    public void iAmOnTheInventoryPage(String type) {
        String url;
        if (type.equals("all")) {
            url = "http://localhost:5173/inventory?category=all";
        } else if (type.startsWith("category")) {
            url = "http://localhost:5173/inventory?category=" + type.split(" ")[1];
        } else if (type.startsWith("warehouse")) {
            url = "http://localhost:5173/inventory?warehouse=" + type.split(" ")[1];
        } else {
            throw new IllegalArgumentException("Invalid page type");
        }
        driver.get(url);
    }

    @When("the inventory has loaded")
    public void theInventoryHasLoaded() {
        assertTrue(this.inventoryPage.inventorySectionLoaded());
    }

    @Then("I should see inventory for {string}")
    public void iShouldSeeInventoryFor(String type) {
        switch (type) {
            case "all" -> assertTrue(this.inventoryPage.iShouldSeeTheInventoryForAnyCategory());
            case "category 4" -> assertTrue(this.inventoryPage.iShouldSeeTheInventoryForASingleCategory());
            default -> assertTrue(this.inventoryPage.iShouldSeeTheInventoryForASingleWarehouse());
        }
    }

    @And("I have opened the create inventory modal")
    public void iHaveOpenedTheCreateInventoryModal() {
        inventoryPage.clickOnCreateInventoryButton();
    }

    @When("I enter valid inputs for all form fields")
    public void iEnterValidInputsForAllFormFields() {
        this.inventoryPage.enterCreationFormInputs("PenguinPro", "Emperor Penguin Shoes", "Really cool shoes", "Climbing Shoes", "CA1", "59.99", "11", "16");
    }

    @And("I press the submit button")
    public void iPressTheSubmitButton() {
        inventoryPage.clickOnModalSubmitButton();
    }

    @Then("the created inventory should appear in the inventory for {string}")
    public void theCreatedInventoryShouldAppearInTheInventory(String type) {
        if (type.equals("warehouse 1") || type.equals("category 4") || type.equals("all")) {
            assertTrue(this.inventoryPage.containsInventoryWithName("Emperor Penguin Shoes"));
        }
    }
}
