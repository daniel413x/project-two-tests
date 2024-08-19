@product-categories-update
Feature: Update product category

  Background:
    Given I am on the product categories page
    When the product categories load
    And I select the "edit" icon on a category card
    Then I should see a category name field pre-filled with the current name
    And edit the category name field to a new value

  Scenario: Save product category update
    Then I should click the "Save" button
    And see the category name updated to the new value

  Scenario: Cancel product category update
    Then I should click the "Cancel" button
    And see the category name unchanged
