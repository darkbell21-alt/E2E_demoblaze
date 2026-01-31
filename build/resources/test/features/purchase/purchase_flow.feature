Feature: Saucedemo Purchase Flow with JSON

  Scenario Outline: Complete purchase for <userKey> with checkout index <index>
    Given I login as user "<userKey>"
    When I add the listed products for user "<userKey>"
    And I view my cart
    And I proceed to checkout
    And I enter checkout data for user "<userKey>" and index <index>
    Then I should see the confirmation for user "<userKey>"

    Examples:
      | userKey        | index |
      | standard_user  | 0     |
