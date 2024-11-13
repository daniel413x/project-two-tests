package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project_two_functional_tests.utils.StateConverter;

public class WarehousesPage {

    private WebDriver driver;
    private JavascriptExecutor js;

    private static final String url = "http://localhost:5173/warehouses";
    private String updatedCardWarehouseName;
    private String updatedCardMaxCapacity;
    private String updatedCardCity;
    private String updatedCardState;
    private String deletedCardWarehouseName;

    @FindBy(className = "ant-card")
    private List<WebElement> cards;

    @FindBy(className = "ant-modal-content")
    private WebElement modal;

    @FindBy(className = "ant-dropdown")
    private WebElement dropdown;

    public WarehousesPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Verify if the current page URL matches the expected URL
    public boolean onPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.getCurrentUrl().equals(url);
    }

    // Navigate to the specified URL
    public void get() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.driver.get(url);
    }

    // Check if the warehouse cards are loaded
    public boolean warehouseCardsLoaded() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cards.size() > 0;
    }

    // Verify if all warehouses are displayed by checking related cards
    public boolean iShouldSeeTheWarehouses() {
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.equals("CA1"))
                return true;
        }
        return false;
    }

    public void clickButton(String buttonText) {
        WebElement button = driver.findElement(By.xpath("//button[span[text()='" + buttonText + "']]"));
        button.click();
    }

    public void focusAndSelectButton(String buttonText) {
        WebElement button = driver.findElement(By.xpath("//button[span[text()='" + buttonText + "']]"));
        js.executeScript("arguments[0].focus();", button);
        button.sendKeys(Keys.ENTER);    
    }

    // Fill out form input in the modal
    public void enterFormInputs(String warehouseName, String maxCapacity, String streetAddress, String city,
            String state, String zipCode) {
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        nameField.sendKeys(warehouseName);
        WebElement maxCapacityField = modal.findElement(By.id("form_in_modal_maxCapacity"));
        maxCapacityField.sendKeys(maxCapacity);
        WebElement streetAddressField = modal.findElement(By.id("form_in_modal_streetAddress"));
        streetAddressField.sendKeys(maxCapacity);
        WebElement cityField = modal.findElement(By.id("form_in_modal_city"));
        cityField.sendKeys(city);
        WebElement stateField = modal.findElement(By.id("form_in_modal_state"));
        stateField.sendKeys(state);
        stateField.sendKeys(Keys.ENTER);
        WebElement zipCodeField = modal.findElement(By.id("form_in_modal_zipCode"));
        zipCodeField.sendKeys(zipCode);
    }

    // Fill out form input in the modal  via keyboard
    public void focusAndEnterFormInputs(String warehouseName, String maxCapacity, String streetAddress, String city,
            String state, String zipCode) {
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        js.executeScript("arguments[0].focus();", nameField);
        nameField.sendKeys(Keys.ENTER);    
        nameField.sendKeys(warehouseName);

        WebElement maxCapacityField = modal.findElement(By.id("form_in_modal_maxCapacity"));
        js.executeScript("arguments[0].focus();", maxCapacityField);
        maxCapacityField.sendKeys(Keys.ENTER);    
        maxCapacityField.sendKeys(maxCapacity);

        WebElement streetAddressField = modal.findElement(By.id("form_in_modal_streetAddress"));
        js.executeScript("arguments[0].focus();", streetAddressField);
        streetAddressField.sendKeys(Keys.ENTER);
        streetAddressField.sendKeys(streetAddress);

        WebElement cityField = modal.findElement(By.id("form_in_modal_city"));
        js.executeScript("arguments[0].focus();", cityField);
        cityField.sendKeys(Keys.ENTER);
        cityField.sendKeys(city);
        
        WebElement stateField = modal.findElement(By.id("form_in_modal_state"));
        js.executeScript("arguments[0].focus();", stateField);
        stateField.sendKeys(Keys.ENTER);
        stateField.sendKeys(state);
        stateField.sendKeys(Keys.ENTER);

        WebElement zipCodeField = modal.findElement(By.id("form_in_modal_zipCode"));
        js.executeScript("arguments[0].focus();", zipCodeField);
        zipCodeField.sendKeys(Keys.ENTER);
        zipCodeField.sendKeys(zipCode);
}

    // Check if any cards contain matching name (returns true if found)
    public boolean containsWarehouseWithName(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.contains(name))
                return true;
        }
        return false;
    }

    // Check if any cards contain matching name (returns false if found)
    public boolean doesNotContainWarehouseWithName(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            if (title.equals(name))
                return false;
        }
        return true;
    }

    // Click on "Edit" or "..." button on card
    public void clickIconOnCard(int index, String iconType) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (iconType) {
            case "edit":
                updatedCardWarehouseName = cards.get(index).findElement(By.className("ant-card-meta-title")).getText();
                updatedCardWarehouseName = updatedCardWarehouseName.replace("Warehouse ", "");

                String[] cityAndState = cards.get(index).findElement(By.className("ant-card-meta-description"))
                        .getText()
                        .split(",");
                updatedCardCity = cityAndState[0].trim();
                updatedCardState = cityAndState[1].trim();

                cards.get(index).findElement(By.className("anticon-edit")).click();
                break;
            case "delete":
                deletedCardWarehouseName = cards.get(index).findElement(By.className("ant-card-meta-title")).getText();
                deletedCardWarehouseName.replace("Warehouse ", "");

                cards.get(index).findElement(By.className("anticon-delete")).click();
                break;
        }
    }

        // Click on "Edit" or "..." button on card
        public void focusAndSelectIconOnCard(int index, String iconType) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            switch (iconType) {
                case "edit":
                    updatedCardWarehouseName = cards.get(index).findElement(By.className("ant-card-meta-title")).getText();
                    updatedCardWarehouseName = updatedCardWarehouseName.replace("Warehouse ", "");
    
                    String[] cityAndState = cards.get(index).findElement(By.className("ant-card-meta-description"))
                            .getText()
                            .split(",");
                    updatedCardCity = cityAndState[0].trim();
                    updatedCardState = cityAndState[1].trim();
    
                    WebElement editIcon = cards.get(index).findElement(By.className("anticon-edit"));
                    js.executeScript("arguments[0].focus();", editIcon);
                    editIcon.sendKeys(Keys.ENTER);   
                    break;
                case "delete":
                    deletedCardWarehouseName = cards.get(index).findElement(By.className("ant-card-meta-title")).getText();
                    deletedCardWarehouseName.replace("Warehouse ", "");
    
                    WebElement deleteIcon = cards.get(index).findElement(By.className("anticon-delete"));
                    js.executeScript("arguments[0].focus();", deleteIcon);
                    deleteIcon.sendKeys(Keys.ENTER);   
                    break;
            }
        }

    // Checks if the form field contains the current warehouse information based on
    // the initial values
    public boolean formFieldsContainCurrentWarehouseInformation() {
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        WebElement cityField = modal.findElement(By.id("form_in_modal_city"));
        WebElement stateField = modal.findElement(By.className("ant-select-selection-item"));

        return nameField.getAttribute("value").equals(updatedCardWarehouseName) &&
                cityField.getAttribute("value").equals(updatedCardCity) &&
                stateField.getAttribute("title").equals(StateConverter.getStateName(updatedCardState));
    }

    // Edits the inputs in the form fields with the specified values, replacing the
    // existing inputs
    public void updateWarehouseNameMaxCapacityStreetAddressCityStateAndZipCode(String warehouseName, String maxCapacity,
            String streetAddress, String city, String state, String zipCode) {
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));

        // Clear method won't work due to the way the initial value of the field is
        // being set
        for (int i = 0; i < updatedCardWarehouseName.length(); i++) {
            nameField.sendKeys(Keys.BACK_SPACE);
        }

        nameField.sendKeys(warehouseName);

        WebElement maxCapacityField = modal.findElement(By.id("form_in_modal_maxCapacity"));
        updatedCardMaxCapacity = maxCapacityField.getAttribute("value");

        for (int i = 0; i < updatedCardMaxCapacity.length(); i++) {
            maxCapacityField.sendKeys(Keys.BACK_SPACE);
        }

        maxCapacityField.sendKeys(maxCapacity);

        WebElement streetAddressField = modal.findElement(By.id("form_in_modal_streetAddress"));
        String updatedCardStreetAddress = streetAddressField.getAttribute("value");

        for (int i = 0; i < updatedCardStreetAddress.length(); i++) {
            streetAddressField.sendKeys(Keys.BACK_SPACE);
        }

        streetAddressField.sendKeys(streetAddress);

        WebElement cityField = modal.findElement(By.id("form_in_modal_city"));

        for (int i = 0; i < updatedCardCity.length(); i++) {
            cityField.sendKeys(Keys.BACK_SPACE);
        }

        cityField.sendKeys(city);

        WebElement stateField = modal.findElement(By.id("form_in_modal_state"));
        String updatedCardStateFull = StateConverter.getStateName(updatedCardState);

        for (int i = 0; i < updatedCardStateFull.length(); i++) {
            stateField.sendKeys(Keys.BACK_SPACE);
        }

        stateField.sendKeys(state);
        stateField.sendKeys(Keys.ENTER);

        WebElement zipCodeField = modal.findElement(By.id("form_in_modal_zipCode"));
        String updatedCardZipCode = streetAddressField.getAttribute("value");

        for (int i = 0; i < updatedCardZipCode.length(); i++) {
            zipCodeField.sendKeys(Keys.BACK_SPACE);
        }

        zipCodeField.sendKeys(zipCode);
    }

    // Edits the inputs in the form fields with the specified values, replacing the
    // existing inputs via keyboard
    public void focusAndUpdateWarehouseNameMaxCapacityStreetAddressCityStateAndZipCode(String warehouseName, String maxCapacity,
            String streetAddress, String city, String state, String zipCode) {
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        js.executeScript("arguments[0].focus();", nameField);
        nameField.sendKeys(Keys.ENTER);

        // Clear method won't work due to the way the initial value of the field is
        // being set
        for (int i = 0; i < updatedCardWarehouseName.length(); i++) {
            nameField.sendKeys(Keys.BACK_SPACE);
        }

        nameField.sendKeys(warehouseName);

        WebElement maxCapacityField = modal.findElement(By.id("form_in_modal_maxCapacity"));
        updatedCardMaxCapacity = maxCapacityField.getAttribute("value");
        js.executeScript("arguments[0].focus();", maxCapacityField);
        maxCapacityField.sendKeys(Keys.ENTER);

        for (int i = 0; i < updatedCardMaxCapacity.length(); i++) {
            maxCapacityField.sendKeys(Keys.BACK_SPACE);
        }

        maxCapacityField.sendKeys(maxCapacity);

        WebElement streetAddressField = modal.findElement(By.id("form_in_modal_streetAddress"));
        String updatedCardStreetAddress = streetAddressField.getAttribute("value");
        js.executeScript("arguments[0].focus();", streetAddressField);
        streetAddressField.sendKeys(Keys.ENTER);

        for (int i = 0; i < updatedCardStreetAddress.length(); i++) {
            streetAddressField.sendKeys(Keys.BACK_SPACE);
        }

        streetAddressField.sendKeys(streetAddress);

        WebElement cityField = modal.findElement(By.id("form_in_modal_city"));
        js.executeScript("arguments[0].focus();", streetAddressField);
        streetAddressField.sendKeys(Keys.ENTER);

        for (int i = 0; i < updatedCardCity.length(); i++) {
            cityField.sendKeys(Keys.BACK_SPACE);
        }

        cityField.sendKeys(city);

        WebElement stateField = modal.findElement(By.id("form_in_modal_state"));
        String updatedCardStateFull = StateConverter.getStateName(updatedCardState);
        js.executeScript("arguments[0].focus();", stateField);
        stateField.sendKeys(Keys.ENTER);

        for (int i = 0; i < updatedCardStateFull.length(); i++) {
            stateField.sendKeys(Keys.BACK_SPACE);
        }

        stateField.sendKeys(state);
        stateField.sendKeys(Keys.ENTER);

        WebElement zipCodeField = modal.findElement(By.id("form_in_modal_zipCode"));
        String updatedCardZipCode = streetAddressField.getAttribute("value");
        js.executeScript("arguments[0].focus();", zipCodeField);
        zipCodeField.sendKeys(Keys.ENTER);

        for (int i = 0; i < updatedCardZipCode.length(); i++) {
            zipCodeField.sendKeys(Keys.BACK_SPACE);
        }

        zipCodeField.sendKeys(zipCode);
    }

    // Checks that card is updated after save by verifying it contains the new
    // information
    public boolean savedCardIsUpdated(String newWarehouseName, String newCity,
            String newState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            String[] cityAndState = card.findElement(By.className("ant-card-meta-description")).getText()
                    .split(",");
            String city = cityAndState[0].trim();
            String state = cityAndState[1].trim();

            if (title.contains(newWarehouseName) && city.equals(newCity)
                    && state.equals(StateConverter.getStateAbbreviation(newState)))
                return true;
        }
        return false;
    }

    // Checks that card is not updated after cancel by verifying it contains the
    // initial information
    public boolean canceledCardIsNotUpdated() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (WebElement card : cards) {
            String title = card.findElement(By.className("ant-card-meta-title")).getText();
            String[] cityAndState = card.findElement(By.className("ant-card-meta-description")).getText()
                    .split(",");
            String city = cityAndState[0].trim();
            String state = cityAndState[1].trim();

            if (title.contains(updatedCardWarehouseName) && city.equals(updatedCardCity)
                    && state.equals(updatedCardState))
                return true;
        }
        return false;
    }

    // Click card with specified warehouse name
    public void clickWarehouseCard(String cardName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (WebElement card : cards) {
            wait.until(ExpectedConditions.visibilityOf(card));

            String title = card.findElement(By.className("ant-card-meta-title")).getText();

            if (title.contains(cardName)) {
                card.click();
                break;
            }
        }
    }

    // Click the warehouse card that was updated
    public void clickWarehouseCard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (WebElement card : cards) {
            wait.until(ExpectedConditions.visibilityOf(card));

            String title = card.findElement(By.className("ant-card-meta-title")).getText();

            if (title.contains(updatedCardWarehouseName)) {
                card.click();
                break;
            }
        }
    }

       // Select card with specified warehouse name via keyboard
       public void focusAndSelectWarehouseCard(String cardName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (WebElement card : cards) {
            wait.until(ExpectedConditions.visibilityOf(card));

            String title = card.findElement(By.className("ant-card-meta-title")).getText();

            if (title.contains(cardName)) {
                js.executeScript("arguments[0].focus();", card);
                card.sendKeys(Keys.ENTER);
                break;
            }
        }
    }

    // Select the warehouse card that was updated via keyboard
    public void focusAndSelectWarehouseCard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (WebElement card : cards) {
            wait.until(ExpectedConditions.visibilityOf(card));

            String title = card.findElement(By.className("ant-card-meta-title")).getText();

            if (title.contains(updatedCardWarehouseName)) {
                js.executeScript("arguments[0].focus();", card);
                card.sendKeys(Keys.ENTER);
                break;
            }
        }
    }

    // Verify max capacity is updated to the new max capacity
    public boolean maxCapacityIsUpdated(String newMaxCapacity) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String h1 = driver.findElement(By.tagName("h1")).getText();

        Pattern pattern = Pattern.compile("\\(\\d+/(\\d+)\\)");
        Matcher matcher = pattern.matcher(h1);

        if (matcher.find()) {
            String maxCapacity = matcher.group(1);
            return maxCapacity.equals(newMaxCapacity);
        } else
            return false;
    }

    // Verify max capacity is not updated and stays the initial max capacity
    public boolean maxCapacityIsNotUpdated() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String h1 = driver.findElement(By.tagName("h1")).getText();

        Pattern pattern = Pattern.compile("\\(\\d+/(\\d+)\\)");
        Matcher matcher = pattern.matcher(h1);

        if (matcher.find()) {
            String maxCapacity = matcher.group(1);
            return maxCapacity.equals(updatedCardMaxCapacity);
        } else
            return false;
    }

    // Checks that card is deleted by verifying no cards it contains the name
    public boolean cardIsDeleted() {
        return this.doesNotContainWarehouseWithName(deletedCardWarehouseName);
    }
}
