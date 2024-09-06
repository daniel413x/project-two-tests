package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final String url = "http://localhost:5173/inventory?category=all";

    private int filteredRowCount;

    private String initialBrand;
    private String initialProductName;
    private String initialDescription;
    private String initialProductType;
    private String initialWarehouse;
    private String initialPrice;
    private String initialSize;
    private String initialQuantity;

    @FindBy(tagName = "thead")
    private WebElement headerRow;

    @FindBy(className = "ant-table-row")
    private List<WebElement> inventoryItemRows;

    @FindBy(className = "ant-btn-primary")
    private WebElement addInventoryButton;

    @FindBy(className = "ant-modal-content")
    private WebElement modal;

    @FindBy(className = "ant-table-filter-dropdown")
    private WebElement searchModal;

    @FindBy(className = "ant-popover-inner")
    private WebElement deletePopover;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Set up explicit wait
        PageFactory.initElements(driver, this);
    }

    // Verify if the current page URL matches the expected URL
    public boolean onPage() {
        try {
            wait.until(ExpectedConditions.urlToBe(url));
            return driver.getCurrentUrl().equals(url);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Navigate to the specified URL
    public void get() {
        this.driver.get(url);
    }

    // Retrieve all inventory item rows from the page
    public List<WebElement> getRows() {
        return this.inventoryItemRows;
    }

    // Check if the inventory section is loaded
    public boolean inventorySectionLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("th")));
            return !this.driver.findElements(By.tagName("th")).isEmpty();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verify if all inventory is displayed by checking related header cells
    public boolean allInventoryIsDisplayed() {
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

        return cellTitles.containsAll(Arrays.asList("Product Type", "Warehouse"));
    }

    // Check if the inventory for a single product category is displayed
    // by checking related header cells exist/do not exist
    public boolean inventoryForSingleProductCategoryIsDisplayed() {
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

        return cellTitles.contains("Warehouse") && !cellTitles.contains("Product Type");
    }

    // Check if the inventory for a single warehouse is displayed by
    // checking related header cells exist/do not exist
    public boolean inventoryForSingleWarehouseIsDisplayed() {
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

        return cellTitles.contains("Product Type") && !cellTitles.contains("Warehouse");
    }

    // Click the button to create new inventory
    public void clickCreateInventoryButton() {
        addInventoryButton.click();
        modal.isDisplayed();
    }

    // Fill out form inputs in the modal
    public void enterFormInputs(String brand, String productName, String description, String productType,
            String warehouse, String price, String size, String quantity) {
        WebElement brandField = modal.findElement(By.id("form_in_modal_brand"));
        brandField.sendKeys(brand);
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        nameField.sendKeys(productName);
        WebElement descriptionField = modal.findElement(By.id("form_in_modal_description"));
        descriptionField.sendKeys(description);
        WebElement productTypeField = modal.findElement(By.id("form_in_modal_categoryName"));
        if (productTypeField.isEnabled()) {
            productTypeField.sendKeys(productType);
            productTypeField.sendKeys(Keys.ENTER);
        }
        WebElement warehouseField = modal.findElement(By.id("form_in_modal_warehouseName"));
        if (warehouseField.isEnabled()) {
            warehouseField.sendKeys(warehouse);
            warehouseField.sendKeys(Keys.ENTER);
        }
        WebElement priceField = modal.findElement(By.id("form_in_modal_price"));
        priceField.sendKeys(price);
        WebElement sizeField = modal.findElement(By.id("form_in_modal_size"));
        sizeField.sendKeys(size);
        WebElement quantityField = modal.findElement(By.id("form_in_modal_quantity"));
        quantityField.sendKeys(quantity);
    }

    // Click the submit button on the modal
    public void clickOnModalSubmitButton() {
        WebElement button = modal.findElement(By.xpath("//button[@type='submit']"));
        button.click();
    }

    // Verify if an inventory item with specific values is displayed
    public boolean inventoryItemDisplayed(String brand, String productName, String description, String productType,
            String warehouse, String price, String size, String quantity) {
        try {
            wait.until(ExpectedConditions.visibilityOf(headerRow));
            wait.until(ExpectedConditions.visibilityOfAllElements(inventoryItemRows));

            List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
            List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

            // Iterates through inventory rows to find a matching row
            for (WebElement row : inventoryItemRows) {

                List<WebElement> rowCells = row.findElements(By.tagName("td"));
                List<String> rowValues = rowCells.stream().map(WebElement::getText).collect(Collectors.toList());

                boolean isMatchingRow = true;

                if (!brand.equals(rowValues.get(cellTitles.indexOf("Brand")))) {
                    isMatchingRow = false;
                }
                if (!productName.equals(rowValues.get(cellTitles.indexOf("Name")))) {
                    isMatchingRow = false;
                }
                if (!description.equals(rowValues.get(cellTitles.indexOf("Description")))) {
                    isMatchingRow = false;
                }

                if (cellTitles.contains("Product Type")) {
                    if (productType != null && !productType.equals(rowValues.get(cellTitles.indexOf("Product Type")))) {
                        isMatchingRow = false;
                    }
                }

                if (cellTitles.contains("Warehouse")) {
                    if (warehouse != null && !warehouse.equals(rowValues.get(cellTitles.indexOf("Warehouse")))) {
                        isMatchingRow = false;
                    }
                }

                if (!price.equals(rowValues.get(cellTitles.indexOf("Price")))) {
                    isMatchingRow = false;
                }
                if (size != null && !size.equals(rowValues.get(cellTitles.indexOf("Size")))) {
                    isMatchingRow = false;
                }
                if (!quantity.equals(rowValues.get(cellTitles.indexOf("Quantity")))) {
                    isMatchingRow = false;
                }

                if (isMatchingRow) {
                    return true;
                }
            }
            return false;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verify if an inventory item with specific values is not displayed
    public boolean inventoryItemNotDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(headerRow));
            wait.until(ExpectedConditions.visibilityOfAllElements(inventoryItemRows));

            List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
            List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

            // Iterates through inventory rows to check none match
            for (WebElement row : inventoryItemRows) {

                List<WebElement> rowCells = row.findElements(By.tagName("td"));
                List<String> rowValues = rowCells.stream().map(WebElement::getText).collect(Collectors.toList());

                boolean isMatchingRow = true;

                if (!initialBrand.equals(rowValues.get(cellTitles.indexOf("Brand")))) {
                    isMatchingRow = false;
                }
                if (!initialProductName.equals(rowValues.get(cellTitles.indexOf("Name")))) {
                    isMatchingRow = false;
                }
                if (initialDescription.equals(rowValues.get(cellTitles.indexOf("Description")))) {
                    isMatchingRow = false;
                }

                if (cellTitles.contains("Product Type")) {
                    if (!initialProductType.equals(rowValues.get(cellTitles.indexOf("Product Type")))) {
                        isMatchingRow = false;
                    }
                }

                if (cellTitles.contains("Warehouse")) {
                    if (!initialWarehouse.equals(rowValues.get(cellTitles.indexOf("Warehouse")))) {
                        isMatchingRow = false;
                    }
                }

                if (!initialPrice.equals(rowValues.get(cellTitles.indexOf("Price")))) {
                    isMatchingRow = false;
                }
                if (!initialSize.equals(rowValues.get(cellTitles.indexOf("Size")))) {
                    isMatchingRow = false;
                }
                if (!initialQuantity.equals(rowValues.get(cellTitles.indexOf("Quantity")))) {
                    isMatchingRow = false;
                }

                if (isMatchingRow) {
                    return false;
                }
            }
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Click on "Edit" or "Delete" action link in inventory row
    public void selectLinkOnRow(int index, String linkText) {
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

        List<WebElement> rowCells = inventoryItemRows.get(index).findElements(By.tagName("td"));

        initialBrand = cellTitles.indexOf("Brand") != -1 ? rowCells.get(cellTitles.indexOf("Brand")).getText() : null;
        initialProductName = cellTitles.indexOf("Name") != -1 ? rowCells.get(cellTitles.indexOf("Name")).getText()
                : null;
        initialDescription = cellTitles.indexOf("Description") != -1
                ? rowCells.get(cellTitles.indexOf("Description")).getText()
                : null;
        initialProductType = cellTitles.indexOf("Product Type") != -1
                ? rowCells.get(cellTitles.indexOf("Product Type")).getText()
                : driver.findElement(By.id("breadcrumb-product-type")) != null
                        ? driver.findElement(By.id("breadcrumb-product-type")).getText()
                        : null;
        initialWarehouse = cellTitles.indexOf("Warehouse") != -1
                ? rowCells.get(cellTitles.indexOf("Warehouse")).getText()
                : driver.findElement(By.id("breadcrumb-warehouse-name")) != null
                        ? driver.findElement(By.id("breadcrumb-warehouse-name")).getText()
                        : null;
        initialPrice = cellTitles.indexOf("Price") != -1 ? rowCells.get(cellTitles.indexOf("Price")).getText() : null;
        initialSize = cellTitles.indexOf("Size") != -1 ? rowCells.get(cellTitles.indexOf("Size")).getText() : null;
        initialQuantity = cellTitles.indexOf("Quantity") != -1 ? rowCells.get(cellTitles.indexOf("Quantity")).getText()
                : null;

        switch (linkText) {
            case "Edit":
                WebElement editLink = inventoryItemRows.get(index).findElement(By.className("ant-btn-link"));
                editLink.click();
                break;
            case "Delete":
                WebElement deleteLink = inventoryItemRows.get(index).findElement(By.id("delete-inventory-" + index));
                deleteLink.click();
                break;
        }
    }

    // Click button to confirm delete in popconfirm modal
    public void pressConfirmDeleteButton(int index) {
        WebElement button = wait
                .until(ExpectedConditions.elementToBeClickable(By.id("confirm-delete-inventory-" + index)));
        button.click();
    }

    // Check if any rows contain matching string (returns false if found)
    public boolean doesNotContainInventoryMatchingString(String name) {
        return this.driver.findElement(By.tagName("main")).getText().contains(name);
    }

    // Click "Save" or "Cancel" button in form modal
    public void clickButtonInModal(String buttonText) {
        switch (buttonText) {
            case "Save":
                modal.findElement(By.xpath("//button[@type='submit']")).click();
                break;
            case "Cancel":
                modal.findElement(By.className("ant-btn-default")).click();
                break;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Click "Search" or "Reset" button in search modal
    public void clickButtonInSearchModal(String buttonText) {
        switch (buttonText) {
            case "Search":
                searchModal.findElement(By.className("ant-btn-primary")).click();
                break;
            case "Reset":
                searchModal.findElement(By.className("ant-btn-default")).click();
                break;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Click sort or search icon in specified column header cell
    public void selectIconOnColumn(String columnName, String iconType) {
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        WebElement columnCell = null;

        for (WebElement cell : headerCells) {
            if (cell.getText().equals(columnName)) {
                columnCell = cell;
                break;
            }
        }

        switch (iconType) {
            case "search":
                WebElement searchIcon = columnCell.findElement(By.className("ant-table-filter-trigger"));
                searchIcon.click();
                break;
            case "sort":
                columnCell.click();
                break;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Check whether rows are sorted correctly alphabetically in ascending
    // order
    public boolean columnRowsAreInAlphaAscOrder(String columnName) {
        if (inventoryItemRows.size() == 1) {
            return true;
        }

        // Find the index of the column for specified column name
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());
        int columnIndex = -1;

        for (int i = 0; i < cellTitles.size(); i++) {
            if (cellTitles.get(i).equals(columnName)) {
                columnIndex = i;
                break;
            }
        }

        List<String> columnValues = new ArrayList<>();
        for (WebElement row : inventoryItemRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            columnValues.add(cells.get(columnIndex).getText());
        }

        // Loop through the list of cell values to check if they are in ascending
        // alphabetical order
        for (int i = 1; i < columnValues.size(); i++) {
            if (columnValues.get(i).compareTo(columnValues.get(i - 1)) < 0) {
                return false;
            }
        }

        return true;
    }

    // Check whether rows are sorted correctly alphabetically in
    // descending order
    public boolean columnRowsAreInAlphaDescOrder(String columnName) {
        if (inventoryItemRows.size() == 1) {
            return true;
        }

        // Find the index of the column for specified column name
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());
        int columnIndex = -1;

        for (int i = 0; i < cellTitles.size(); i++) {
            if (cellTitles.get(i).equals(columnName)) {
                columnIndex = i;
                break;
            }
        }

        List<String> columnValues = new ArrayList<>();
        for (WebElement row : inventoryItemRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            columnValues.add(cells.get(columnIndex).getText());
        }

        // Loop through the list of cell values to check if they are in descending
        // alphabetical order
        for (int i = 1; i < columnValues.size(); i++) {
            if (columnValues.get(i).compareTo(columnValues.get(i - 1)) > 0) {
                return false;
            }
        }

        return true;
    }

    // Check whether rows are sorted correctly numerically in
    // ascending order
    public boolean columnRowsAreInNumericAscOrder(String columnName) {
        if (inventoryItemRows.size() == 1) {
            return true;
        }

        // Find the index of the column for specified column name
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());
        int columnIndex = -1;

        for (int i = 0; i < cellTitles.size(); i++) {
            if (cellTitles.get(i).equals(columnName)) {
                columnIndex = i;
                break;
            }
        }

        List<Double> columnValues = new ArrayList<>();
        for (WebElement row : inventoryItemRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String text = cells.get(columnIndex).getText();
            try {
                columnValues.add(Double.parseDouble(text)); // convert to double
            } catch (NumberFormatException e) {
                this.columnRowsAreInAlphaAscOrder(columnName);
            }
        }

        // Loop through the list of cell values to check if they are in ascending
        // numeric order
        for (int i = 1; i < columnValues.size(); i++) {
            if (columnValues.get(i) < columnValues.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    // Check whether rows are sorted correctly numerically in descending order
    public boolean columnRowsAreInNumericDescOrder(String columnName) {
        if (inventoryItemRows.size() == 1) {
            return true;
        }

        // Find the index of the column for specified column name
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());
        int columnIndex = -1;

        for (int i = 0; i < cellTitles.size(); i++) {
            if (cellTitles.get(i).equals(columnName)) {
                columnIndex = i;
                break;
            }
        }

        List<Double> columnValues = new ArrayList<>();
        for (WebElement row : inventoryItemRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String text = cells.get(columnIndex).getText();
            try {
                columnValues.add(Double.parseDouble(text));
            } catch (NumberFormatException e) {
                this.columnRowsAreInAlphaDescOrder(columnName);
            }
        }

        // Loop through the list of cell values to check if they are in descending
        // numeric order
        for (int i = 1; i < columnValues.size(); i++) {
            if (columnValues.get(i) > columnValues.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    // Check if the search modal is displayed
    public boolean searchModalIsDisplayed() {
        return searchModal.isDisplayed();
    }

    // Enter the specified input into the search field in the search modal
    public void enterInputInSearchField(String input) {
        WebElement searchField = searchModal.findElement(By.tagName("input"));
        searchField.sendKeys(input);
    }

    // Check if the rows in the specified column contain the input value (indicating
    // they are filtered)
    public boolean columnRowsAreFiltered(String columnName, String value) {
        // Find the index of the column for specified column name
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());
        int columnIndex = -1;

        for (int i = 0; i < cellTitles.size(); i++) {
            if (cellTitles.get(i).equals(columnName)) {
                columnIndex = i;
                break;
            }
        }

        List<String> columnValues = new ArrayList<>();
        for (WebElement row : inventoryItemRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            columnValues.add(cells.get(columnIndex).getText());
        }

        // Check if all values in the column contain specified value
        for (int i = 1; i < columnValues.size(); i++) {
            if (!columnValues.get(i).contains(value)) {
                return false;
            }
        }

        return true;
    }

    // Checks if the number of rows is equal to or greater than filter row count
    // (indicating filter is no longer applied)
    public boolean columnRowsAreNotFiltered() {
        return inventoryItemRows.size() >= filteredRowCount;
    }

    // Searches a column for a specific value by opening the search modal, entering
    // the value, and clicking "Search"
    public void searchColumnForValue(String columnName, String value) {
        this.selectIconOnColumn(columnName, "search");
        if (this.searchModalIsDisplayed()) {
            this.enterInputInSearchField(value);
            this.clickButtonInSearchModal("Search");

            this.filteredRowCount = inventoryItemRows.size();
        }
    }

    // Checks if the form fields contain the current inventory item information
    // based on the initial values
    public boolean formFieldsContainCurrentInventoryItemInformation() {
        WebElement brandField = modal.findElement(By.id("form_in_modal_brand"));
        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));
        WebElement descriptionField = modal.findElement(By.id("form_in_modal_description"));
        WebElement productTypeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//input[@id='form_in_modal_categoryName']/ancestor::div[@class='ant-select-selector']//span[@class='ant-select-selection-item']")));
        WebElement warehouseField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//input[@id='form_in_modal_warehouseName']/ancestor::div[@class='ant-select-selector']//span[@class='ant-select-selection-item']")));
        WebElement priceField = modal.findElement(By.id("form_in_modal_price"));
        WebElement sizeField = modal.findElement(By.id("form_in_modal_size"));
        WebElement quantityField = modal.findElement(By.id("form_in_modal_quantity"));

        // Compare the current values in the fields with the initial values
        return (brandField.getAttribute("value").equals(initialBrand) &&
                nameField.getAttribute("value").equals(initialProductName) &&
                descriptionField.getAttribute("value").equals(initialDescription) &&
                productTypeField.getAttribute("title").equals(initialProductType) &&
                warehouseField.getAttribute("title").equals(initialWarehouse) &&
                priceField.getAttribute("value").equals(initialPrice) &&
                sizeField.getAttribute("value").equals(initialSize) &&
                quantityField.getAttribute("value").equals(initialQuantity));
    }

    // Edits the input in the form fields with the specified values, replacing the
    // existing input
    public void editFormInput(String brand, String productName, String description, String productType,
            String warehouse, String price, String size,
            String quantity) {
        WebElement brandField = modal.findElement(By.id("form_in_modal_brand"));

        // Must loop through word and backspace it as clear method doesn't work here
        for (int i = 0; i < initialBrand.length(); i++) {
            brandField.sendKeys(Keys.BACK_SPACE);
        }

        brandField.sendKeys(brand);

        WebElement nameField = modal.findElement(By.id("form_in_modal_name"));

        for (int i = 0; i < initialProductName.length(); i++) {
            nameField.sendKeys(Keys.BACK_SPACE);
        }

        nameField.sendKeys(productName);

        WebElement descriptionField = modal.findElement(By.id("form_in_modal_description"));

        for (int i = 0; i < initialDescription.length(); i++) {
            descriptionField.sendKeys(Keys.BACK_SPACE);
        }

        descriptionField.sendKeys(description);

        WebElement productTypeField = modal.findElement(By.id("form_in_modal_categoryName"));

        if (productTypeField.isEnabled()) {
            for (int i = 0; i < initialProductType.length(); i++) {
                productTypeField.sendKeys(Keys.BACK_SPACE);
            }

            productTypeField.sendKeys(productType);
            productTypeField.sendKeys(Keys.ENTER);
        }

        WebElement warehouseField = modal.findElement(By.id("form_in_modal_warehouseName"));

        if (warehouseField.isEnabled()) {
            for (int i = 0; i < initialWarehouse.length(); i++) {
                warehouseField.sendKeys(Keys.BACK_SPACE);
            }

            warehouseField.sendKeys(warehouse);
            warehouseField.sendKeys(Keys.ENTER);
        }

        WebElement priceField = modal.findElement(By.id("form_in_modal_price"));

        for (int i = 0; i < initialPrice.length(); i++) {
            priceField.sendKeys(Keys.BACK_SPACE);
        }

        priceField.sendKeys(price);

        WebElement sizeField = modal.findElement(By.id("form_in_modal_size"));

        for (int i = 0; i < initialSize.length(); i++) {
            sizeField.sendKeys(Keys.BACK_SPACE);
        }

        sizeField.sendKeys(size);

        WebElement quantityField = modal.findElement(By.id("form_in_modal_quantity"));

        for (int i = 0; i < initialQuantity.length(); i++) {
            quantityField.sendKeys(Keys.BACK_SPACE);
        }

        quantityField.sendKeys(quantity);
    }

    // Checks if the inventory item was not updated by comparing the current and
    // initial values
    public boolean inventoryItemNotUpdated() {
        try {
            wait.until(ExpectedConditions.visibilityOf(headerRow));
            wait.until(ExpectedConditions.visibilityOfAllElements(inventoryItemRows));

            List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
            List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

            for (WebElement row : inventoryItemRows) {

                List<WebElement> rowCells = row.findElements(By.tagName("td"));
                List<String> rowValues = rowCells.stream().map(WebElement::getText).collect(Collectors.toList());

                boolean isMatchingRow = true;

                if (!initialBrand.equals(rowValues.get(cellTitles.indexOf("Brand")))) {
                    isMatchingRow = false;
                }
                if (!initialProductName.equals(rowValues.get(cellTitles.indexOf("Name")))) {
                    isMatchingRow = false;
                }
                if (!initialDescription.equals(rowValues.get(cellTitles.indexOf("Description")))) {
                    isMatchingRow = false;
                }

                if (cellTitles.contains("Product Type")) {
                    if (!initialProductType.equals(rowValues.get(cellTitles.indexOf("Product Type")))) {
                        isMatchingRow = false;
                    }
                }

                if (cellTitles.contains("Warehouse")) {
                    if (!initialWarehouse.equals(rowValues.get(cellTitles.indexOf("Warehouse")))) {
                        isMatchingRow = false;
                    }
                }

                if (!initialPrice.equals(rowValues.get(cellTitles.indexOf("Price")))) {
                    isMatchingRow = false;
                }
                if (!initialSize.equals(rowValues.get(cellTitles.indexOf("Size")))) {
                    isMatchingRow = false;
                }
                if (!initialQuantity.equals(rowValues.get(cellTitles.indexOf("Quantity")))) {
                    isMatchingRow = false;
                }

                if (isMatchingRow) {
                    return true;
                }
            }
            return false;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }
}
