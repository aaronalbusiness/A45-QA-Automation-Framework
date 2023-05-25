Feature: Login Feature

  Scenario: Login loginSuccessfulTest
    Given I open login page
    When I enter email "demo@class.com"
    And I enter password "te$t$tudent"
    And I submit
    Then I am logged in


  Scenario: Login loginInvalidEmailValidPassswordTest
    Given I open login page
    When I enter email "demo"
    And I enter password "te$t$tudent"
    And I submit
    Then I am not logged in


  Scenario: Login loginValidEmailInvalidPassswordTest
    Given I open login page
    When I enter email "demo@class.com"
    And I enter password "te$t"
    And I submit
    Then I am not logged in


  Scenario: Login loginEmptyEmailEmptyPasswordTest
    Given I open login page
    When I enter email ""
    And I enter password ""
    And I submit
    Then I am not logged in

