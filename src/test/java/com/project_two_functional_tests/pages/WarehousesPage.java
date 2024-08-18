package com.project_two_functional_tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class WarehousesPage {

    private WebDriver driver;
    private static final String url = "http://localhost:5173/warehouses";

    @FindBy(className="cards")
    private WebElement warehouseCards;

    @FindBy(tagName = "button")
    private List<WebElement> addWarehouseButton;

    @FindBy(className = "ant-modal-content")
    private WebElement modal;

    public WarehousesPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean onPage() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return driver.getCurrentUrl().equals(url);
    }

    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    public boolean warehouseCardsLoaded() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return !warehouseCards.findElements(By.tagName("div")).isEmpty();
    }

    public boolean iShouldSeeTheProductCategories() {
        return warehouseCards.getText().contains("CA1");
    }

    public void clickOnCreateWarehouseButton() {
        WebElement button = addWarehouseButton.get(0);
        button.click();
        modal.isDisplayed();
    }

    public void enterFormInputs(String name, String capacity, String address, String city, String state, String zip) {
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        nameField.sendKeys(name);
        WebElement maxCapacityField = modal.findElement(By.id("form_in_modal_maxCapacity"));
        maxCapacityField.sendKeys(capacity);
        WebElement streetAddressField = modal.findElement(By.id("form_in_modal_streetAddress"));
        streetAddressField.sendKeys(address);
        WebElement cityField = modal.findElement(By.id("form_in_modal_city"));
        cityField.sendKeys(city);
        WebElement stateField = modal.findElement(By.id("form_in_modal_state"));
        stateField.sendKeys(state);
        stateField.sendKeys(Keys.ENTER);
        WebElement zipCodeField = modal.findElement(By.id("form_in_modal_zipCode"));
        zipCodeField.sendKeys(zip);
    }

    public void clickOnModalSubmitButton() {
        WebElement button = modal.findElement(By.xpath("//button[@type='submit']"));
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        button.click();
    }

    public boolean containsWarehouseWithName(String name) {
        return warehouseCards.getText().contains(name);
    }

    public boolean doesNotContainWarehouseWithName(String name) {
        return !warehouseCards.getText().contains(name);
    }
}
