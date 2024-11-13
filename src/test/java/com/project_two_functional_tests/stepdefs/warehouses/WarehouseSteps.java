package com.project_two_functional_tests.stepdefs.warehouses;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.project_two_functional_tests.pages.WarehousesPage;
import com.project_two_functional_tests.utils.HeadlessChromeDriver;
import com.project_two_functional_tests.utils.ResetDatabase;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WarehouseSteps {

    private WebDriver driver;
    private WarehousesPage warehousesPage;
    
    private String testWarehouseName = "DC1";
    private String testMaxCapacity = "1800";
    private String testStreetAddress = "4000 Connecticut Avenue NW";
    private String testCity = "Washington";
    private String testState = "District of Columbia"; // Go DC Statehood
    private String testZipCode = "20009";

    @BeforeAll
    public static void resetDatabaseBeforeAll() {
        ResetDatabase.run();
    }

    @Before("@warehouses or @warehouse-creation or @warehouse-update or @warehouse-delete or @keyboard-warehouse-creation or @keyboard-warehouse-update or @keyboard-warehouse-delete")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.warehousesPage = new WarehousesPage(driver);
        driver.manage().window().maximize();
    }

    @After("@warehouses or @warehouse-creation or @warehouse-update or @warehouse-delete or @keyboard-warehouse-creation or @keyboard-warehouse-update or @keyboard-warehouse-delete")
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

    /**
     * 01-warehouses.feature
     * Scenario: Warehouse are shown 
     */

    @Then("I should see warehouses")
    public void iShouldSeeTheWarehouses() {
        assertTrue(this.warehousesPage.containsWarehouseWithName("CA1"));
    }

    /**
     * 02-warehouses-creation.feature
     * Scenario: A new warehouse is created
     */

    @Then("I click the {string} button")
    public void iClickTheButton(String buttonText) {
        this.warehousesPage.clickButton(buttonText);
    }

    @Then("I focus and select the {string} button")
    public void iFocusAndSelectTheButton(String buttonText) {
        this.warehousesPage.focusAndSelectButton(buttonText);
    }

    @Then("I enter valid input for a new warehouse into the modal form")
    public void iEnterValidInput() {
        this.warehousesPage.enterFormInputs(testWarehouseName, testMaxCapacity, testStreetAddress, testCity, testState,
                testZipCode);
    }

    @Then("I focus and enter valid input for a new warehouse into the modal form")
    public void iFocusAndEnterInput() {
        this.warehousesPage.focusAndEnterFormInputs(testWarehouseName, testMaxCapacity, testStreetAddress, testCity, testState,
                testZipCode);
    }

    @Then("the created warehouse should appear in the list of warehouses")
    public void theCreatedWarehouseShouldAppearInTheListOfWarehouses() {
        assertTrue(this.warehousesPage.containsWarehouseWithName(testWarehouseName));
    }

    /**
     * 03-warehouse-update.feature, 04-warehouse-delete.feature
     * Features: Update warehouse, Delete warehouse
     */

    @And("I click the {string} icon on a warehouse card")
    public void iClickTheIconOnACard(String iconType) {
        this.warehousesPage.clickIconOnCard(0, iconType);
    }


    @And("I focus and select the {string} icon on a warehouse card")
    public void iFocusAndSelectTheIconOnACard(String iconType) {
        this.warehousesPage.focusAndSelectIconOnCard(0, iconType);
    }

    /**
     * 03-warehouse-update.feature
     * Scenarios: Save warehouse update,  Cancel warehouse update
     */

    @Then("I should see a form with pre-filled fields of current warehouse information")
    public void iShouldSeeWarehouseFormFieldsPrefilled() {
        assertTrue(this.warehousesPage.formFieldsContainCurrentWarehouseInformation());
    }

    @And("update the Warehouse Name, Max Capacity, Street Address, City, State, and Zip Code fields")
    public void updateWarehouseNameMaxCapacityStreetAddressCityStateAndZipCode() {
        this.warehousesPage.updateWarehouseNameMaxCapacityStreetAddressCityStateAndZipCode(testWarehouseName,
                testMaxCapacity, testStreetAddress, testCity, testState, testZipCode);
    }

    @And("focus and update the Warehouse Name, Max Capacity, Street Address, City, State, and Zip Code fields")
    public void focusAndUpdateWarehouseNameMaxCapacityStreetAddressCityStateAndZipCode() {
        // this.warehousesPage.updateWarehouseNameMaxCapacityStreetAddressCityStateAndZipCode(testWarehouseName,
        //         testMaxCapacity, testStreetAddress, testCity, testState, testZipCode);
    }

    @And("should see the warehouse name, city, and state updated")
    public void seeWarehouseNameCityAndStateUpdated() {
        assertTrue(this.warehousesPage.savedCardIsUpdated(testWarehouseName, testCity, testState));
    }

    @And("should see the warehouse name, city, and state unchanged")
    public void seeWarehouseNameCityAndStateUnchanged() {
        assertTrue(this.warehousesPage.canceledCardIsNotUpdated());
    }

    @Then("I click the updated warehouse card")
    public void iClickUpdatedWarehouseCard() {
        this.warehousesPage.clickWarehouseCard(testWarehouseName);
    }

    @Then("I focus and select the updated warehouse card")
    public void iFocusAndSelectUpdatedWarehouseCard() {
        // this.warehousesPage.selectWarehouseCard(testWarehouseName);
    }

    @Then("I click the unchanged warehouse card")
    public void iClickUnchangedWarehouseCard() {
        this.warehousesPage.clickWarehouseCard();
    }

    @Then("I focus and select the unchanged warehouse card")
    public void iFocusAndSelectUnchangedWarehouseCard() {
        // this.warehousesPage.selectWarehouseCard();
    }

    @And("should see the max capacity updated")
    public void shouldSeeMaxCapacityUpdated() {
        assertTrue(this.warehousesPage.maxCapacityIsUpdated(testMaxCapacity));
    }

    @And("should see the max capacity unchanged")
    public void shouldSeeMaxCapacityUnchanged() {
        assertTrue(this.warehousesPage.maxCapacityIsNotUpdated());
    }

    /**
     * 04-warehouse-delete.feature
     * Scenarios: Delete warehouse
     */
    
    @Then("I should not see the warehouse card displayed")
    public void iShouldNotSeeWarehouseCardDisplayed() {
        assertTrue(this.warehousesPage.cardIsDeleted());
    }

}
