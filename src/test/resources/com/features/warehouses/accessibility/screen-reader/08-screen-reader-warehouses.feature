@screen-reader-warehouses
Feature: Warehouses shown via screen reader

  Scenario: Warehouse are shown via screen reader
    Given I am on the warehouses page
    And a screen reader is enabled
    When the warehouses load
    Then the screen reader should announce the page title as "Warehouses - Crag Supply Co."
