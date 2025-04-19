Feature: Todo functionality

 Background:
  Given I open the TodoMVC application

  @PositiveScenario:
    Scenario: Add a new todo item
    When I add a todo item "Learn Tosca"
    Then I should see "Learn Tosca" in the list


  @PositiveScenario:
  Scenario: Mark todo as completed

    When I add a todo item "Selenium"
    And I mark the item "Selenium" as completed
    Then the item "Selenium" should be marked completed


  @PositiveScenario:
  Scenario: Edit an existing todo item

    When I add a todo item "Original Task"
    And I edit the todo item from "Original Task" to "Edited Task"
    Then I should see "Edited Task" in the list

  @PositiveScenario:
  Scenario: Delete a todo item

    When I add a todo item "Delete me"
    And I delete the item "Delete me"
    Then the list should be empty

  @PositiveScenario:
  Scenario: Clear completed todo items

    When I add two todo items "Task 1" and "Task 2"
    And I mark "Task 1" as completed
    And I click on the "Clear Completed" button
    Then only "Task 2" should remain in the list

  @PositiveScenario:
  Scenario: User can filter the list by "All"

    When I add todo items "Learn Cucumber" and "Complete Cucumber tutorial"
    And I mark "Complete Cucumber tutorial" as completed
    And I click on the "All" filter
    Then I should see both "Learn Cucumber" and "Complete Cucumber tutorial" in the list


  @PositiveScenario:
  Scenario: User can filter the list by "Active"

    When I add todo items "Learn Java" and "Complete Java tutorial"
    And I mark "Complete Java tutorial" as completed
    And I click on the "Active" filter
    Then I should see "Learn Java" in the list
    And I should not see "Complete Java tutorial" in the list

  @PositiveScenario:
  Scenario: User can filter the list by "Completed"

    When I add todo items "Learn API" and "Complete API tutorial"
    And I mark "Complete API tutorial" as completed
    And I click on the "Completed" filter
    Then I should see "Complete API tutorial" in the list
    And I should not see "Learn API" in the list

  @PositiveScenario:
  Scenario: User can clear all completed todos with a single click

    When I add todo items "Learn Katalon" and "Complete Katalon tutorial"
    And I mark "Complete Katalon tutorial" as completed
    And I click on the "Clear Completed" button
    Then I should not see "Complete Katalon tutorial" in the list
    And I should see "Learn Katalon" in the list

  @PositiveScenario:
  Scenario: Add a todo with valid due date
    When I enter "Book flight tickets 25-01-2025" as the todo
    And I press Enter
    Then I should see "Book flight tickets 25-01-2025" in the list

  @NegativeScenario
  Scenario: Add an empty todo item
    When I leave the todo input empty and press Enter
    Then I should not see any new todo added to the list

  @NegativeScenario
  Scenario: Add a todo item with only spaces
    When I enter "     " in the todo input and press Enter
    Then I should not see any new todo added to the list

  @NegativeScenario
  Scenario: Add a very long todo item
    When I enter a string with more than 1000 characters in the todo input and press Enter
    Then the todo should be added without breaking the UI


  @NegativeScenario
  Scenario: Edit a todo and leave it blank
    Given I have added a todo item "Sample Task"
    When I double-click to edit the todo and clear its content and press Enter
    Then the todo should not be removed from the list

  @NegativeScenario
  Scenario: Edit a todo and input only spaces
    Given I have added a todo item "Task To Edit"
    When I double-click to edit the todo, input spaces and press Enter
    Then the todo should not be updated with empty spaces

  @NegativeScenario
  Scenario: Rapidly delete multiple todos
    Given I have added multiple todos
    When I quickly click the delete button on each todo
    Then all todos should be deleted without breaking the app


  @NegativeScenario:
  Scenario: Filter completed tasks when none are completed
    When I click the "Completed" filter with no completed todos
    Then I should see an empty list and no errors

 @Negative
  Scenario: Reload the app after adding todos
    Given I have added a todo item "Reload check"
   When I reload the page
  Then the todo should still be present

