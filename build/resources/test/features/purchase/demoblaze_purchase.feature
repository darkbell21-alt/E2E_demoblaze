@demoblaze
Feature: Demoblaze Purchase Flow

  Scenario Outline: Complete purchase for <userKey> with checkout index <index>
    Given I open demoblaze home
    When I add the listed products for demoblaze user "<userKey>"
    And I view my demoblaze cart
    And I place order for demoblaze user "<userKey>" and index <index>
    Then I should see the demoblaze confirmation for user "<userKey>"

    Examples:
      | userKey    | index |
      | demo_user  | 0     |
