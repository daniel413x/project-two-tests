@screen-reader-warehouse-delete
Feature: Delete warehouse via screen reader

  Scenario: Delete warehouse via screen reader
    Given I am on the warehouses page
    And a screen reader is enabled
    When the warehouses load
    And I focus and activate the "delete" icon on a warehouse card
    Then the screen reader should announce that the warehouse was deleted