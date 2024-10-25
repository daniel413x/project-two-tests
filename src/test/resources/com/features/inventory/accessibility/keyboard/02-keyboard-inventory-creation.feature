@keyboard-inventory-creation
Feature: Inventory creation via keyboard

  Scenario Outline: Create a new item in inventory for the all inventory, warehouse inventory, and product category inventory pages via keyboard
    Given I am on the "<inventoryPage>" inventory page
    When I tab and select the "Add Inventory" button
    Then I enter valid inputs for all form fields
    And I submit the form
    Then I should see the new inventory item row displayed

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
