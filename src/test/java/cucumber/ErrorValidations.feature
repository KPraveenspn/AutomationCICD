
@tag
Feature: Error Validations
 I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Negative Test to validate the error message
   Given I landed on Ecommerce Page
   When Logged in with username <name> and password <password>
   Then "Incorrect email or password." message is displayed
   
   Examples:
     | name              | password        |
     | shetty@gmail.com  | Iamtester@0     |
     