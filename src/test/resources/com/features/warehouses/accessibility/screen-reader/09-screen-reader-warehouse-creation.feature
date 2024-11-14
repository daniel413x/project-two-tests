@screen-reader-warehouse-creation
Feature: Warehouse creation via screen reader

  Scenario: A new warehouse is created via screen reader
    Given I am on the warehouses page
    And a screen reader is enabled
    When the warehouses load
    Then I focus and activate the "Add Warehouse" button
    Then I focus, activate, and enter valid input for a new warehouse into the modal form
    And I focus and activate the "Create" button
    Then the screen reader should confirm the new warehouse was created