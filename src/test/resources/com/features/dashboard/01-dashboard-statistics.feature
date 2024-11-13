@dashboard-statistics
Feature: Dashboard Statistics

  Background:
    Given I am on the dashboard page
    When the dashboard loads

  Scenario: Read total inventory items
    Then I should see the "Total Items in Inventory" with a value of "1145"

  Scenario: Read total max capacity
    Then I should see the "Total Max Capacity" with a value of "3700"
