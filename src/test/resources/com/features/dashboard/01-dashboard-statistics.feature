@dashboard-statistics
Feature: Dashboard Statistics

  Background:
    Given I am on the dashboard page
    When the dashboard loads

  Scenario: Total inventory items
    Then I should see the "Total Items in Inventory" with a value of "1145"

  Scenario: Total max capacity
    Then I should see the "Total Max Capacity" with a value of "3700"
