@screen-reader-inventory-creation
Feature: Inventory creation via screen reader

# Last step not implemented
  Scenario Outline: Create a new item in inventory for the all inventory, warehouse inventory, and product category inventory pages via screen reader
    Given I am on the "<inventoryPage>" inventory page
    And a screen reader is enabled
    When the inventory has loaded
    Then I focus and activate the "Add Inventory" button
    Then I focus, activate, and enter valid inputs for all form fields
    And I focus and activate the "Create" button
    Then the screen reader should confirm the new item was created

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
