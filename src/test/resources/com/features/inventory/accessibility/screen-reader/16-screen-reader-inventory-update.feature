@screen-reader-inventory-update
Feature: Update inventory items via screen reader

# Last step not implemented
  Scenario: Save inventory item update on all inventory page via screen reader
    Given I am on the "all" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And focus, activate, and update the Brand, Product Name, Description, Product Type, Warehouse, Price, Size and Quantity fields
    Then I focus and activate the "Save" button
    And the screen reader should announce that the inventory item row is updated

# Last step not implemented
  Scenario Outline: Save inventory item update on a warehouse inventory page via screen reader
    Given I am on the "<inventorybyWarehousePage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And focus, activate, and update the Brand, Product Name, Description, Product Type, Price, Size and Quantity fields
    Then I focus and activate the "Save" button
    And the screen reader should announce that the inventory item row is updated

    Examples:
      | inventorybyWarehousePage |
      | warehouse 1              |
      | warehouse 2              |

# Last step not implemented
  Scenario Outline: Save inventory item update on a product category page via screen reader
    Given I am on the "<inventorybyProductCategoryPage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And focus, activate, and update the Brand, Product Name, Description, Warehouse, Price, Size and Quantity fields
    Then I focus and activate the "Save" button
    And the screen reader should announce that the inventory item row is updated

    Examples:
      | inventorybyProductCategoryPage |
      | category 1                     |
      | category 2                     |

  Scenario: Cancel inventory item update on all inventory page via screen reader
    Given I am on the "all" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And focus, activate, and update the Brand, Product Name, Description, Product Type, Warehouse, Price, Size and Quantity fields
    Then I focus and activate the "Cancel" button
    And the screen reader should announce that the inventory item row remains unchanged

# Last step not implemented
  Scenario Outline: Cancel inventory item update on a warehouse inventory page via screen reader
    Given I am on the "<inventorybyWarehousePage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And focus, activate, and update the Brand, Product Name, Description, Product Type, Warehouse, Price, Size and Quantity fields
    Then I focus and activate the "Cancel" button
    And should see the inventory item row unchanged

    Examples:
      | inventorybyWarehousePage |
      | warehouse 1              |
      | warehouse 2              |

# Last step not implemented
  Scenario Outline: Cancel inventory item update on a product category page via screen reader
    Given I am on the "<inventorybyProductCategoryPage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    And I focus and activate the "Edit" link on a row
    Then I should see a form with pre-filled fields of current inventory item information
    And focus, activate, and update the Brand, Product Name, Description, Warehouse, Price, Size and Quantity fields
    Then I focus and activate the "Cancel" button
    And the screen reader should announce that the inventory item row remains unchanged

    Examples:
      | inventorybyProductCategoryPage |
      | category 1                     |
      | category 2                     |