@inventory-creation
Feature: Inventory creation

  Scenario Outline: Create a new item in inventory for the all inventory, warehouse inventory, and product category inventory pages
    Given I am on the "<inventoryPage>" inventory page
    And I have opened the create inventory modal
    When I enter valid inputs for all form fields
    And I press the submit button
    Then I should see the new inventory item row displayed

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
