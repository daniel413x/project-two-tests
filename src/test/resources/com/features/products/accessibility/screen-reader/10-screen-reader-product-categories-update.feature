@screen-reader-product-categories-update
Feature: Update product category via screen reader

  Background:
    Given I am on the product categories page
    And a screen reader is enabled
    When the product categories load
    And I focus and activate the "edit" icon on a category card

  Scenario Outline: Save product category update via screen reader
    Then the screen reader should announce the "Update <category>" modal
    And I focus, activate, and update the category name field to a new value
    Then I focus and activate the "Save" button
    And the screen reader should announce that the category is updated

    Examples:
      | category       |
      | Climbing Shoes |
      | Ropes          |

  Scenario Outline: Cancel product category update via screen reader
    Then the screen reader should announce the "Update <category>" modal
    And I focus, activate, and update the category name field to a new value
    Then I focus and activate the "Cancel" button
    And the screen reader should announce that the category remains unchanged

    Examples:
      | category       |
      | Climbing Shoes |
      | Ropes          |
