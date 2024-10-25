@keyboard-inventory-update
Feature: Update inventory items via keyboard

  Scenario: Save inventory item update on all inventory page via keyboard
    Given I am on the "all" inventory page
    When the inventory has loaded
    And I tab and select the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And edit the Brand, Product Name, Description, Product Type, Warehouse, Price, Size and Quantity fields
    Then I should tab and select the "Save" button
    And see the inventory item row updated

  Scenario Outline: Save inventory item update on a warehouse inventory page via keyboard
    Given I am on the "<inventorybyWarehousePage>" inventory page
    When the inventory has loaded
    And I tab and select the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And edit the Brand, Product Name, Description, Product Type, Price, Size and Quantity fields
    Then I should tab and select the "Save" button
    And see the inventory item row updated

    Examples:
      | inventorybyWarehousePage |
      | warehouse 1              |
      | warehouse 2              |

  Scenario Outline: Save inventory item update on a product category page via keyboard
    Given I am on the "<inventorybyProductCategoryPage>" inventory page
    When the inventory has loaded
    And I tab and select the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And edit the Brand, Product Name, Description, Warehouse, Price, Size and Quantity fields
    Then I should tab and select the "Save" button
    And see the inventory item row updated

    Examples:
      | inventorybyProductCategoryPage |
      | category 1                     |
      | category 2                     |

  Scenario: Cancel inventory item update on all inventory page via keyboard
    Given I am on the "all" inventory page
    When the inventory has loaded
    And I tab and select the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And edit the Brand, Product Name, Description, Product Type, Warehouse, Price, Size and Quantity fields
    Then I should tab and select the "Cancel" button
    And see the inventory item row unchanged

  Scenario Outline: Cancel inventory item update on a warehouse inventory page via keyboard
    Given I am on the "<inventorybyWarehousePage>" inventory page
    When the inventory has loaded
    And I tab and select the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And edit the Brand, Product Name, Description, Product Type, Warehouse, Price, Size and Quantity fields
    Then I should tab and select the "Cancel" button
    And see the inventory item row unchanged

    Examples:
      | inventorybyWarehousePage |
      | warehouse 1              |
      | warehouse 2              |

  Scenario Outline: Cancel inventory item update on a product category page via keyboard
    Given I am on the "<inventorybyProductCategoryPage>" inventory page
    When the inventory has loaded
    And I tab and select the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And edit the Brand, Product Name, Description, Warehouse, Price, Size and Quantity fields
    Then I should tab and select the "Cancel" button
    And see the inventory item row unchanged

    Examples:
      | inventorybyProductCategoryPage |
      | category 1                     |
      | category 2                     |