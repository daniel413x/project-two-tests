@warehouses-categories
Feature: Warehouses

  Scenario: Warehouse are shown
    Given I am on the warehouses page
    When the warehouses load
    Then I should see rows of warehouses
