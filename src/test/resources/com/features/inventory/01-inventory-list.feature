@inventory-list
Feature: Inventory lists

  Scenario Outline: View lists of inventory corresponding to the all inventory, warehouse inventory, and product category inventory pages
    Given I am on the "<inventoryPage>" inventory page
    When the inventory has loaded
    Then I should see inventory for "<inventoryPage>"

    Examples:
      | inventoryPage |
      | all           |
      | category 4    |
      | warehouse 1   |
