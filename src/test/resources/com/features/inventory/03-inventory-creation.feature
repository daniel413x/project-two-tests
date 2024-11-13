@inventory-creation
Feature: Inventory creation

  Scenario Outline: Create a new item in inventory for the all inventory, warehouse inventory, and product category inventory pages
    Given I am on the "<inventoryPage>" inventory page
    When the inventory has loaded
    Then I click the "Add Inventory" button
    Then I enter valid inputs for all form fields
    And I click the "Create" button
    Then I should see the new inventory item row displayed

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
