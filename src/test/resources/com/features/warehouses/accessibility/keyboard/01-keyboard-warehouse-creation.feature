@keyboard-warehouse-creation
Feature: Warehouse creation via keyboard

  Scenario: A new warehouse is created via keyboard
    Given I am on the warehouses page
    When the warehouses load
    Then I focus and select the "Add Warehouse" button
    Then I focus and enter valid input for a new warehouse into the modal form
    And I focus and select the "Create" button
    Then the created warehouse should appear in the list of warehouses
