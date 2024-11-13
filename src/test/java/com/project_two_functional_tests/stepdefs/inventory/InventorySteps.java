package com.project_two_functional_tests.stepdefs.inventory;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.project_two_functional_tests.pages.InventoryPage;
import com.project_two_functional_tests.utils.HeadlessChromeDriver;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventorySteps {

    private WebDriver driver;
    private InventoryPage inventoryPage;

    private String testBrand = "PenguinPro";
    private String testProductName = "Emperor Penguin Shoes";
    private String testDescription = "These shoes offer a sleek, penguin-like design, featuring a waterproof shell and an ultra-insulated interior to keep your feet dry and warm, even in the iciest of conditions.";
    private String testProductType = "Climbing Shoes";
    private String testWarehouse = "CA1";
    private String testPrice = "59.99";
    private String testSize = "11";
    private String testQuantity = "16";

    @BeforeAll
    public static void resetDatabaseBeforeAll() {
        ResetDatabase.run();
    }

    @Before("@inventory-list or @inventory-creation or @inventory-sort or @inventory-search or @inventory-update or @inventory-delete or @keyboard-inventory-creation or @keyboard-inventory-sort or @keyboard-inventory-search or @keyboard-inventory-update or @keyboard-inventory-delete")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.inventoryPage = new InventoryPage(driver);
        driver.manage().window().maximize();
    }

    @After("@inventory-list or @inventory-creation or @inventory-sort or @inventory-search or @inventory-update or @inventory-delete or @keyboard-inventory-creation or @keyboard-inventory-sort or @keyboard-inventory-search or @keyboard-inventory-update or @keyboard-inventory-delete")
    public void after() {
        if (this.driver != null) {
            this.driver.quit();
            ResetDatabase.run();
        }
    }

    /**
     * 01-inventory-list.feature
     * Feature: Inventory lists
     */

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
            case "all" -> assertTrue(this.inventoryPage.allInventoryIsDisplayed());
            case "category 4" -> assertTrue(this.inventoryPage.inventoryForSingleProductCategoryIsDisplayed());
            default -> assertTrue(this.inventoryPage.inventoryForSingleWarehouseIsDisplayed());
        }
    }

    /**
     * 03-inventory-creation.feature
     * Scenario Outline: Create a new item in inventory for the all inventory, warehouse inventory, and product category inventory pages
     */

    @Then("I click the {string} button")
    public void iClickTheButton(String buttonText) {
        this.inventoryPage.clickButton(buttonText);
    }

    @Then("I focus and select the {string} button")
    public void iFocusAndSelectTheButton(String buttonText) {
        this.inventoryPage.focusAndSelectButton(buttonText);
    }

    @When("I enter valid inputs for all form fields")
    public void iEnterValidInputsForAllFormFields() {
        this.inventoryPage.enterFormInputs(testBrand, testProductName, testDescription, testProductType, testWarehouse,
                testPrice, testSize, testQuantity);
    }

    @When("I focus and enter valid inputs for all form fields")
    public void iFocusAndEnterValidInputsForAllFormFields() {
        this.inventoryPage.focusAndEnterFormInputs(testBrand, testProductName, testDescription, testProductType, testWarehouse,
                testPrice, testSize, testQuantity);
    }

    @Then("I should see the new inventory item row displayed")
    public void iShouldSeeNewInventoryItemRowDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(this.inventoryPage.inventoryItemDisplayed(testBrand, testProductName, testDescription,
                testProductType, testWarehouse, testPrice, testSize, testQuantity));
    }

    /**
     * 03-inventory-delete.feature
     * Scenario Outline: Delete an inventory item for the all inventory, warehouse inventory, and product category inventory pages
     */
     
    @And("I click the {string} link on a row")
    public void iClickLinkOnARow(String linkText) {
        this.inventoryPage.clickLinkOnRow(0, linkText);
    }

    @And("I focus and select the {string} link on a row")
    public void iFocusAndSelectLinkOnARow(String linkText) {
        this.inventoryPage.focusAndSelectLinkOnRow(0, linkText);
    }

    @Then("I click the Delete button in the popover")
    public void iClickDeleteButtonInPopover() {
        inventoryPage.clickConfirmDeleteButton(0);
    }

    @Then("I focus and select the Delete button in the popover")
    public void iFocusAndSelectDeleteButtonInPopover() {
        inventoryPage.focusAndSelectConfirmDeleteButton(0);
    }

    @Then("I should not see the inventory item row displayed")
    public void iShouldNotSeeInventoryItemDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        assertTrue(inventoryPage.inventoryItemNotDisplayed());
    }

    /**
     * 05-inventory-update.feature
     * Scenarios: 
     * Save inventory item update on all inventory page
     * Save inventory item update on a warehouse inventory page
     * Save inventory item update on a product category page
     */
     
    @Then("I should see a form with pre-filled fields of current inventory item information")
    public void iShouldSeeInventoryItemFormFieldsPrefilled() {
        assertTrue(this.inventoryPage.formFieldsContainCurrentInventoryItemInformation());
    }

    @And("update the Brand, Product Name, Description, Product Type, Warehouse, Price, Size and Quantity fields")
    public void updateBrandProductNameDescriptionProductTypeWarehousePriceSizeAndQuantity() {
        this.inventoryPage.updateFormInput(testBrand, testProductName, testDescription, testProductType, testWarehouse,
                testPrice, testSize, testQuantity);
    }

    @And("update the Brand, Product Name, Description, Warehouse, Price, Size and Quantity fields")
    public void updateBrandProductNameDescriptionWarehousePriceSizeAndQuantity() {
        this.inventoryPage.updateFormInput(testBrand, testProductName, testDescription, null, testWarehouse, testPrice,
                testSize, testQuantity);
    }

    @And("update the Brand, Product Name, Description, Product Type, Price, Size and Quantity fields")
    public void updateBrandProductNameDescriptionProductTypePriceSizeAndQuantity() {
        this.inventoryPage.updateFormInput(testBrand, testProductName, testDescription, testProductType, null, testPrice,
                testSize, testQuantity);
    }

    @And("focus and update the Brand, Product Name, Description, Product Type, Warehouse, Price, Size and Quantity fields")
    public void focusAndUpdateBrandProductNameDescriptionProductTypeWarehousePriceSizeAndQuantity() {
        this.inventoryPage.focusAndUpdateFormInput(testBrand, testProductName, testDescription, testProductType, testWarehouse,
                testPrice, testSize, testQuantity);
    }

    @And("focus and update the Brand, Product Name, Description, Warehouse, Price, Size and Quantity fields")
    public void focusAndUpdateBrandProductNameDescriptionWarehousePriceSizeAndQuantity() {
        this.inventoryPage.focusAndUpdateFormInput(testBrand, testProductName, testDescription, null, testWarehouse, testPrice,
                testSize, testQuantity);
    }

    @And("focus and update the Brand, Product Name, Description, Product Type, Price, Size and Quantity fields")
    public void focusAndUpdateBrandProductNameDescriptionProductTypePriceSizeAndQuantity() {
        this.inventoryPage.focusAndUpdateFormInput(testBrand, testProductName, testDescription, testProductType, null, testPrice,
                testSize, testQuantity);
    }

    @And("should see the inventory item row updated")
    public void seeInventoryItemUpdated() {
        assertTrue(this.inventoryPage.inventoryItemDisplayed(testBrand, testProductName, testDescription,
                testProductType, testWarehouse, testPrice, testSize, testQuantity));
    }

    /**
     * 05-inventory-update.feature
     * Scenarios: 
     * Cancel inventory item update on all inventory page
     * Cancel inventory item update on a warehouse inventory page
     * Cancel inventory item update on a product category page
     */
     
    @And("should see the inventory item row unchanged")
    public void seeInventoryItemUnchanged() {
        assertTrue(this.inventoryPage.inventoryItemNotUpdated());
    }

    /**
     * 04-inventory-search.feature
     * Scenario Outlines:
     * Search inventory items on all inventory page
     * Reset search of inventory items on all inventory page
     * Search inventory items on a warehouse inventory page
     * Reset search of inventory items on a warehouse inventory page
     * Reset search of inventory items on a product category page
     */

    @And("I have searched {string} for {string}")
    public void iHaveSearchedColumnForValue(String columnName, String value) {
        this.inventoryPage.searchColumnForValue(columnName, value);
    }

    @And("I click the {string} icon on the {string} column")
    public void iClickIconOnColumn(String iconType, String columnName) {
        this.inventoryPage.clickIconOnColumn(columnName, iconType);
    }

    @And("I focus and select the {string} icon on the {string} column")
    public void iFocusAndSelectIconOnColumn(String iconType, String columnName) {
        this.inventoryPage.focusAndSelectIconOnColumn(columnName, iconType);
    }

    @Then("I should see a search modal displayed")
    public void iShouldSeeSearchModalDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(this.inventoryPage.searchModalIsDisplayed());
    }

    @Then("I should input {string} in the search field")
    public void iShouldInputValueInSearchField(String value) {
        this.inventoryPage.enterInputInSearchField(value);
    }

    @Then("I should see rows displayed where the {string} column has a value that contains {string}")
    public void iShouldSeeRowsWhereColumnHasValueThatContains(String columnName, String value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(this.inventoryPage.columnRowsAreFiltered(columnName, value));
    }

    @And("I should see all rows displayed where the column contains any value")
    public void iShouldSeeAllRowsWhereColumnHasAnyValue() {
        this.inventoryPage.columnRowsAreNotFiltered();
    }

    @Then("{string} rows should be in {string} order")
    public void rowsShouldBeInOrder(String columnName, String columnOrder) {
        if (columnName.equals("Price") || columnName.equals("Quantity")) {
            switch (columnOrder) {
                case "asc" -> assertTrue(this.inventoryPage.columnRowsAreInNumericAscOrder(columnName));
                case "desc" -> assertTrue(this.inventoryPage.columnRowsAreInNumericDescOrder(columnName));
            }
        } else {
            switch (columnOrder) {
                case "asc" -> assertTrue(this.inventoryPage.columnRowsAreInAlphaAscOrder(columnName));
                case "desc" -> assertTrue(this.inventoryPage.columnRowsAreInAlphaDescOrder(columnName));
            }
        }
    }
}
