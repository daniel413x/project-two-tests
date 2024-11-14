@screen-reader-warehouse-update
Feature: Update warehouse via screen reader

  Background:
    Given I am on the warehouses page
    And a screen reader is enabled
    When the warehouses load
    And I focus and activate the "edit" icon on a warehouse card

  Scenario Outline: Save warehouse update via screen reader
    Then the screen reader should announce the "Update Warehouse <warehouse>" modal
    And focus, activate, and update the Warehouse Name, Max Capacity, Street Address, City, State, and Zip Code fields
    Then I focus and activate the "Save" button
    And should see the warehouse name, city, and state updated
    Then I focus and activate the updated warehouse card
    And should see the max capacity updated

    Examples:
      | warehouse |
      | CA1       |
      | NY1       |

  Scenario Outline: Cancel warehouse update via screen reader
    Then the screen reader should announce the "Update Warehouse <warehouse>" modal
    And focus, activate, and update the Warehouse Name, Max Capacity, Street Address, City, State, and Zip Code fields
    Then I focus and activate the "Cancel" button
    And should see the warehouse name, city, and state unchanged
    Then I focus and activate the unchanged warehouse card
    And should see the max capacity unchanged

    Examples:
      | warehouse |
      | CA1       |
      | NY1       |
