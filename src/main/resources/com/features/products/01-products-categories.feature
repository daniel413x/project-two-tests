@products-categories
Feature: Products

  Background:
    Given I am on the products page

  Scenario: Product categories are shown
    When the data loads
    Then I should see product categories
