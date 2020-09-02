@API
Feature: Test API via REST Assured
  Background: User uses default header to access application
    Given We use default header

  @WS_Regression
  Scenario: As API mock is ready
#    When We send a GET request to '/api/users' endpoint
    When We send a POST request to '/posts' endpoint with body:
    """
    {
      "title": "foo",
      "body": "bar",
      "userId": 1
    }
    """
    Then We got the Response with status code '201'
    And We got the Response with body:
    """
    {
      "title": "foo",
      "body": "bar",
      "userId": 1,
      "id": 101
    }
    """