@screen-reader-dashboard-statistics
Feature: Dashboard Statistics via screen reader

  Background:
    Given I am on the dashboard page
    And a screen reader is enabled
    When the dashboard loads

  Scenario: Read total inventory items via screen reader
    Then the screen reader should announce "Total Items in Inventory" followed by  of "1145"

  Scenario: Read total max capacity via screen reader
    Then the screen reader should announce "Total Max Capacity" followed by a value of "3700"
