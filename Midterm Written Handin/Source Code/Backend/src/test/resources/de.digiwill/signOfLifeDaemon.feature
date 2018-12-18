Feature: Sign of life daemon
  As the Server
  I want to make sure all actions are triggered when necessary
  and all reminders are sent out when necessary

  Background:
    Given The user is logged in
    And a duration is set in profile settings

  Scenario: 01 - Get a reminder
    When Counter is started
    And Counter is expired
    And I have navigated to main page
    Then I should see a reminder in the notification area

  Scenario: 02 - Trigger configured activities
    When Counter is started
    And Counter is expired
    And I have ignored all reminders
    Then I can not login anymore
    And The configured activities should be triggered