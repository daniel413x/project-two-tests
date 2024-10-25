@keyboard-inventory-delete
Feature: Inventory delete via keyboard

  Scenario Outline: Delete an inventory item for the all inventory, warehouse inventory, and product category inventory pages via keyboard
    Given I am on the "<inventoryPage>" inventory page
    When the inventory has loaded
    And I tab and select the "Delete" link on a row
    Then I tab and select the Delete button in the popover
    Then I should not see the inventory item row displayed

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
