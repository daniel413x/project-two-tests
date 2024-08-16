@dashboard-statistics
Feature: Dashboard Statistics

  Scenario: Accurate test statistics are shown
    Given I am on the dashboard page
    When the dashboard loads
    Then I should see the "Total Items in Inventory" with a value of 300
    And I should see the "Total Max Capacity" with a value of 1000
