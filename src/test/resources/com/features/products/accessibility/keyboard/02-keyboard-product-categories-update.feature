@keyboard-product-categories-update
Feature: Update product category via keyboard

  Background:
    Given I am on the product categories page
    When the product categories load
    And I focus and select the "edit" icon on a category card
    Then I should see a category name field pre-filled with the current name
    And update the category name field to a new value

  Scenario: Save product category update via keyboard
    Then I focus and select the "Save" button
    And should see the category name updated to the new value

  Scenario: Cancel product category update via keyboard
    Then I focus and select the "Cancel" button
    And should see the category name unchanged
