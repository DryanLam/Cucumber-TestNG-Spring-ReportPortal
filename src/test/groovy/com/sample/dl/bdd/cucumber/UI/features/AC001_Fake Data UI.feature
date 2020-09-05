@UI
Feature: Web App
  As a normal user
  I am able to get data from UI

  Background: User is already on homepage
    Given I navigate to home page

  @UI_Browsers
  Scenario Outline: I can see 3 key sells on homepage
    When I refresh the homepage
    Then The '<key-sell>' should be displayed correctly

    Examples:
      | key-sell       |
      | Fake data      |
      | Real responses |
      | Always-on      |


  @UI_Browsers_01
  Scenario Outline: I can get empty responses
    When I click on '<http-method>' of '<request-type>' at Give it a try
    Then The request '<request-uri>' is display on the left column
    And The response code '<response-code>'is displayed on the right column
    And The response data'<response-data>' is displayed under right column

    Examples:
      | http-method | request-type                | request-uri     | response-code | response-data |
      | GET         | SINGLE USER NOT FOUND       | /api/users/23   | 404           | {}            |
      | GET         | SINGLE <RESOURCE> NOT FOUND | /api/unknown/23 | 404           | {}            |


