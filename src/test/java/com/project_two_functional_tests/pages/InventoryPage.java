package com.project_two_functional_tests.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    private WebDriver driver;
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

    @FindBy(id = "inventory")
    private WebElement inventorySection;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean onPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
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

    public List<WebElement> getRows() {
        return this.inventoryItemRows;
    }

    public boolean inventorySectionLoaded() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return !inventorySection.findElements(By.tagName("div")).isEmpty();
    }

    // TODO: define helper code on the frontend for more substantial "inventory
    // list" tests
    public boolean allInventoryIsDisplayed() {
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

        return cellTitles.containsAll(Arrays.asList("Product Type", "Warehouse"));
    }

    public boolean inventoryForSingleProductCategoryIsDisplayed() {
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

        return cellTitles.contains("Warehouse") && !cellTitles.contains("Product Type");
    }

    public boolean inventoryForSingleWarehouseIsDisplayed() {
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

        return cellTitles.contains("Product Type") && !cellTitles.contains("Warehouse");
    }

    public void clickCreateInventoryButton() {
        addInventoryButton.click();
        modal.isDisplayed();
    }

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

    public void clickOnModalSubmitButton() {
        WebElement button = modal.findElement(By.xpath("//button[@type='submit']"));
        button.click();
    }

    public boolean inventoryItemDisplayed(String brand, String productName, String description, String productType,
            String warehouse, String price, String size, String quantity) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> headerCells = headerRow.findElements(By.tagName("th"));
        List<String> cellTitles = headerCells.stream().map(WebElement::getText).collect(Collectors.toList());

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
    }

    public boolean inventoryItemNotDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    }

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

    public void pressConfirmDeleteButton(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait
                .until(ExpectedConditions.elementToBeClickable(By.id("confirm-delete-inventory-" + index)));
        button.click();
    }

    public boolean doesNotContainInventoryMatchingString(String name) {
        return !inventorySection.getText().contains(name);
    }

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

    public boolean columnRowsAreInAlphaAscOrder(String columnName) {
        if (inventoryItemRows.size() == 1) {
            return true;
        }

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

        for (int i = 1; i < columnValues.size(); i++) {
            if (columnValues.get(i).compareTo(columnValues.get(i - 1)) < 0) {
                return false;
            }
        }

        return true;
    }

    public boolean columnRowsAreInAlphaDescOrder(String columnName) {
        if (inventoryItemRows.size() == 1) {
            return true;
        }

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

        for (int i = 1; i < columnValues.size(); i++) {
            if (columnValues.get(i).compareTo(columnValues.get(i - 1)) > 0) {
                return false;
            }
        }

        return true;
    }

    public boolean columnRowsAreInNumericAscOrder(String columnName) {
        if (inventoryItemRows.size() == 1) {
            return true;
        }

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
                this.columnRowsAreInAlphaAscOrder(columnName);
            }
        }

        for (int i = 1; i < columnValues.size(); i++) {
            if (columnValues.get(i) < columnValues.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean columnRowsAreInNumericDescOrder(String columnName) {
        if (inventoryItemRows.size() == 1) {
            return true;
        }

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

        for (int i = 1; i < columnValues.size(); i++) {
            if (columnValues.get(i) > columnValues.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean searchModalIsDisplayed() {
        return searchModal.isDisplayed();
    }

    public void enterInputInSearchField(String input) {
        WebElement searchField = searchModal.findElement(By.tagName("input"));
        searchField.sendKeys(input);
    }

    public boolean columnRowsAreFiltered(String columnName, String value) {
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

        for (int i = 1; i < columnValues.size(); i++) {
            if (!columnValues.get(i).contains(value)) {
                return false;
            }
        }

        return true;
    }

    public boolean columnRowsAreNotFiltered() {
        return inventoryItemRows.size() >= filteredRowCount;
    }

    public void searchColumnForValue(String columnName, String value) {
        this.selectIconOnColumn(columnName, "search");
        if (this.searchModalIsDisplayed()) {
            this.enterInputInSearchField(value);
            this.clickButtonInSearchModal("Search");

            this.filteredRowCount = inventoryItemRows.size();
        }
    }

    public boolean formFieldsContainCurrentInventoryItemInformation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));

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

        return (brandField.getAttribute("value").equals(initialBrand) &&
                nameField.getAttribute("value").equals(initialProductName) &&
                descriptionField.getAttribute("value").equals(initialDescription) &&
                productTypeField.getAttribute("title").equals(initialProductType) &&
                warehouseField.getAttribute("title").equals(initialWarehouse) &&
                priceField.getAttribute("value").equals(initialPrice) &&
                sizeField.getAttribute("value").equals(initialSize) &&
                quantityField.getAttribute("value").equals(initialQuantity));
    }

    public void editFormInput(String brand, String productName, String description, String productType,
            String warehouse, String price, String size,
            String quantity) {
        WebElement brandField = modal.findElement(By.id("form_in_modal_brand"));

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

    public boolean inventoryItemNotUpdated() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
    }

}
