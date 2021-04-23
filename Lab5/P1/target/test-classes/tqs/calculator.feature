Feature: Basic Algorithm
  Background:
    Given a Calculator I just turned On

  Scenario:
    When I add 1 and 2
    Then the result is 3

  Scenario:
    When I subtract 1 to 2
    Then the result is 1

  Scenario Outline: Several Additions
    When I add <a> and <b>
    Then the result is <c>

  Examples: Single Digits
    | a | b | c |
    | 1 | 2 | 3 |
    | 3 | 6 | 9 |