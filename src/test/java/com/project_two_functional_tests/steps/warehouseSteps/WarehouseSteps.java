package com.project_two_functional_tests.steps.warehouseSteps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.project_two_functional_tests.pages.WarehousesPage;
import com.project_two_functional_tests.utils.HeadlessChromeDriver;
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
    private String testWarehouseName = "DC1";
    private String testMaxCapacity = "1800";
    private String testStreetAddress = "4000 Connecticut Avenue NW";
    private String testCity = "Washington";
    private String testState = "District of Columbia"; // Go DC Statehood
    private String testZipCode = "20009";

    @Before("@warehouses or @warehouse-creation or @warehouse-update or @warehouse-delete")
    public void before() {
        driver = new HeadlessChromeDriver().getDriver();
        this.warehousesPage = new WarehousesPage(driver);
        driver.manage().window().maximize();
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

    @Then("I should see warehouses")
    public void iShouldSeeTheWarehouses() {
        assertTrue(this.warehousesPage.containsWarehouseWithName("CA1"));
    }

    @And("I have opened the create warehouse form modal")
    public void iHaveOpenedTheCreateWarehouseFormModal() {
        this.warehousesPage.clickOnCreateWarehouseButton();
    }

    @When("I enter valid input for a new warehouse")
    public void iEnterValidInput() {
        this.warehousesPage.enterFormInputs(testWarehouseName, testMaxCapacity, testStreetAddress, testCity, testState,
                testZipCode);
    }

    @And("I press the warehouse form submit button")
    public void iPressTheSubmitButton() {
        this.warehousesPage.clickOnModalSubmitButton();
    }

    @Then("the created warehouse should appear in the list of warehouses")
    public void theCreatedProductCategoryShouldAppearInTheListOfWarehouses() {
        assertTrue(this.warehousesPage.containsWarehouseWithName(testWarehouseName));
    }

    @And("I select the {string} icon on a warehouse card")
    public void iSelectTheIconOnACard(String iconType) {
        this.warehousesPage.selectIconOnCard(0, iconType);
    }

    @Then("I should see a form with pre-filled fields of current warehouse information")
    public void iShouldSeeWarehouseFormFieldsPrefilled() {
        assertTrue(this.warehousesPage.formFieldsContainCurrentWarehouseInformation());
    }

    @And("edit the Warehouse Name, Max Capacity, Street Address, City, State, and Zip Code fields")
    public void editWarehouseNameMaxCapacityStreetAddressCityStateAndZipCode() {
        this.warehousesPage.editWarehouseNameMaxCapacityStreetAddressCityStateAndZipCode(testWarehouseName, testMaxCapacity, testStreetAddress, testCity, testState, testZipCode);
    }

    @Then("I should click the {string} button")
    public void iShouldClickTheButton(String buttonText) {
        this.warehousesPage.clickButtonInModal(buttonText);
    }

    @And("see the warehouse name, city, and state updated")
    public void seeWarehouseNameCityAndStateUpdated() {
        assertTrue(this.warehousesPage.savedCardIsUpdated(testWarehouseName, testCity, testState));
    }

    @And("see the warehouse name, city, and state unchanged")
    public void seeWarehouseNameCityAndStateUnchanged() {
        assertTrue(this.warehousesPage.canceledCardIsNotUpdated());
    }

    @Then("I should select the updated warehouse card")
    public void iShouldSelectUpdatedWarehouseCard() {
        this.warehousesPage.selectWarehouseCard(testWarehouseName);
    }

    @Then("I should select the unchanged warehouse card")
    public void iShouldSelectUnchangedWarehouseCard() {
        this.warehousesPage.selectWarehouseCard();
    }

    @And("see the max capacity updated")
    public void seeMaxCapacityUpdated() {
        assertTrue(this.warehousesPage.maxCapacityIsUpdated(testMaxCapacity));
    }

    @And("see the max capacity unchanged")
    public void seeMaxCapacityUnchanged() {
        assertTrue(this.warehousesPage.maxCapacityIsNotUpdated());
    }

    @And("select Delete from the dropdown")
    public void selectDeleteFromDropdown() {
        this.warehousesPage.selectDeleteDropdownOption();
    }

    @Then("I should not see the warehouse card displayed")
    public void iShouldNotSeeWarehouseCardDisplayed() {
        assertTrue(this.warehousesPage.cardIsDeleted());
    }

}
