package stepdefs;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

public class TodoSteps {

    WebDriver driver = DriverManager.getDriver(); // Get driver from DriverManager

    @Given("I open the TodoMVC application")
    public void open_todo_mvc_app() {
        driver.get("https://todomvc.com/examples/react/dist/");
    }


    @When("I add a todo item {string}")
    public void i_add_a_todo_item(String task) {
        WebElement input = driver.findElement(By.id("todo-input"));
        input.sendKeys(task + Keys.ENTER);
    }


    @Then("I should see {string} in the list")
    public void i_should_see_in_the_list(String task) {
        WebElement item = driver.findElement(By.cssSelector(".todo-list li"));
        assertTrue(item.getText().contains(task));
    }

    @When("I mark the item {string} as completed")
    public void i_mark_the_item_as_completed(String task) {
        WebElement checkbox = driver.findElement(By.cssSelector(".toggle"));
        checkbox.click();
    }

    @Then("the item {string} should be marked completed")
    public void the_item_should_be_marked_completed(String task) {
        WebElement item = driver.findElement(By.cssSelector(".todo-list li"));
        String className = item.getAttribute("class");
        assertTrue(className.contains("completed"));
    }

    @When("I edit the todo item from {string} to {string}")
    public void editTodoItem(String originalText, String newText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Locate the label with the original task text
        WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='" + originalText + "']")));

        // Double-click the label to enable editing
//        Actions actions = new Actions(driver);
//        actions.doubleClick(label).perform();

        new Actions(driver)
                .moveToElement(label)
                .doubleClick()
                .doubleClick()
                // at this point the <input class="edit"> is focused
                .sendKeys( Keys.DELETE)
                .sendKeys(newText)
                .sendKeys(Keys.RETURN)
                .perform();

//        // Locate the edit input field that appears after double-click
//        WebElement editInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//label[text()='Original Task']")));
//
//        // Clear the existing value and input the new text
//        editInput.clear();
//        editInput.sendKeys(newText);
//        editInput.sendKeys(Keys.RETURN);
  }



    @When("I delete the item {string}")
    public void i_delete_the_item(String task) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement todo = driver.findElement(By.xpath("//label[contains(text(), '" + task + "')]"));

        // Hover over the todo item to reveal the delete button
        Actions actions = new Actions(driver);
        actions.moveToElement(todo).perform();

        // Wait for the delete button to be visible and then click it
        WebElement deleteBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".destroy")));
        deleteBtn.click();
    }

    @Then("the list should be empty")
    public void the_list_should_be_empty() {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        assertEquals(todos.size(), 0);

    }

    @When("I click on the {string} button")
    public void i_click_on_the_button(String buttonName) {
        if (buttonName.equals("Clear Completed")) {
            WebElement clearCompletedBtn = driver.findElement(By.className("clear-completed"));
            clearCompletedBtn.click();
        }
    }

    @Then("only {string} should remain in the list")
    public void only_should_remain_in_the_list(String remainingTask) {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        assertTrue(todos.stream().anyMatch(todo -> todo.getText().contains(remainingTask)));
    }

    @When("I add two todo items {string} and {string}")
    public void i_add_two_todo_items(String task1, String task2) {
        WebElement input = driver.findElement(By.className("new-todo"));
        input.sendKeys(task1);
        input.sendKeys(Keys.ENTER);
        input.sendKeys(task2);
        input.sendKeys(Keys.ENTER);
    }


    @When("I mark {string} as completed")
    public void i_mark_task_as_completed(String taskName) {
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list li"));

        for (WebElement item : todoItems) {
            String label = item.findElement(By.tagName("label")).getText();
            if (label.equals(taskName)) {
                WebElement checkbox = item.findElement(By.cssSelector("input.toggle"));
                checkbox.click();  // Mark as completed
                break;
            }
        }
    }

    @When("I click on the {string} filter")
    public void i_click_on_the_filter(String filterName) {
        WebElement filter;
        switch (filterName) {
            case "All":
                filter = driver.findElement(By.linkText("All"));
                break;
            case "Active":
                filter = driver.findElement(By.linkText("Active"));
                break;
            case "Completed":
                filter = driver.findElement(By.linkText("Completed"));
                break;
            default:
                throw new IllegalArgumentException("Unknown filter: " + filterName);
        }
        filter.click();
    }

    @When("I add todo items {string} and {string}")
    public void i_add_todo_items(String task1, String task2) {
        WebElement input = driver.findElement(By.className("new-todo"));
        input.sendKeys(task1);
        input.sendKeys(Keys.ENTER); // Add first task
        input.sendKeys(task2);
        input.sendKeys(Keys.ENTER); // Add second task
    }

    @Then("I should see both {string} and {string} in the list")
    public void i_should_see_both_in_the_list(String task1, String task2) {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        boolean task1Found = todos.stream().anyMatch(todo -> todo.getText().contains(task1));
        boolean task2Found = todos.stream().anyMatch(todo -> todo.getText().contains(task2));
        assertTrue(task1Found, "Task not found: " + task1);
        assertTrue(task2Found, "Task not found: " + task2);
    }


    @Then("I should not see {string} in the list")
    public void i_should_not_see_in_the_list(String task) {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        boolean taskFound = todos.stream().anyMatch(todo -> todo.getText().contains(task));
        assertFalse(taskFound, "Unexpected task found: " + task);
    }

//    @Given("I open the TodoMVC React app")
//    public void i_open_the_TodoMVC_React_app() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("https://todomvc.com/examples/react/dist/");
//        driver.manage().window().maximize();
//        todoInput = driver.findElement(By.className("new-todo"));
//    }

    @When("I leave the todo input empty and press Enter")

    public void i_leave_the_todo_input_empty_and_press_enter() {
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        todoInput.sendKeys(Keys.ENTER);
    }

    @Then("I should not see any new todo added to the list")
    public void i_should_not_see_any_new_todo_added_to_the_list() {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        Assert.assertEquals(0, todos.size());

    }

    @When("I enter {string} in the todo input and press Enter")
    public void i_enter_string_in_the_todo_input_and_press_enter(String todo) {
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        todoInput.sendKeys(todo);
        todoInput.sendKeys(Keys.ENTER);
    }

    @Then("the todo should be added without breaking the UI")
    public void the_todo_should_be_added_without_breaking_the_ui() {
        WebElement firstTodo = driver.findElement(By.cssSelector(".todo-list li"));
        Assert.assertTrue(firstTodo.isDisplayed());

    }


    @Given("I have added a todo item {string}")
    public void i_have_added_a_todo_item(String task) {
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        todoInput.sendKeys(task);
        todoInput.sendKeys(Keys.ENTER);
    }

    @When("I double-click to edit the todo and clear its content and press Enter")
    public void i_edit_the_todo_and_clear_content() {
        // Locate and double-click on the todo label to enter edit mode
        WebElement todoLabel = driver.findElement(By.xpath("//label[text()='Sample Task']"));

        new Actions(driver)
                .moveToElement(todoLabel)
                .doubleClick()
                .doubleClick()
                // at this point the <input class="edit"> is focused
                .sendKeys( Keys.DELETE)
                .sendKeys(Keys.RETURN)
                .perform(); // Press Enter to save the empty content
    }

    @Then("the todo should not be removed from the list")
    public void the_todo_should_not_be_removed_from_the_list() {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        boolean todo= todos.isEmpty();
        Assert.assertFalse(todo, "Todo should still exist");

    }

    @When("I double-click to edit the todo, input spaces and press Enter")
    public void i_edit_the_todo_with_spaces() {
        // Locate the label and double-click to enter edit mode
        WebElement todoLabel = driver.findElement(By.xpath("//label[text()='Task To Edit']"));

        new Actions(driver)
                .moveToElement(todoLabel)
                .doubleClick()
                // at this point the <input class="edit"> is focused
                .sendKeys( Keys.SPACE)
                .sendKeys(Keys.RETURN)
                .perform(); // Press Enter to save the empty content
    }


    @When("I enter a string with more than 1000 characters in the todo input and press Enter")
    public void i_enter_a_very_long_string_in_the_todo_input() {
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        StringBuilder longText = new StringBuilder();
        while (longText.length() <= 1000) {
            longText.append("This is a long todo. ");
        }
        todoInput.sendKeys(longText.toString());
        todoInput.sendKeys(Keys.ENTER);
    }

    @Given("I have added multiple todos")
    public void i_have_added_multiple_todos() {
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        String[] todos = {"Task 1", "Task 2", "Task 3", "Task 4"};
        for (String task : todos) {
            todoInput.sendKeys(task);
            todoInput.sendKeys(Keys.ENTER);
        }
    }

    @Then("the todo should not be updated with empty spaces")
    public void the_todo_should_not_be_updated_with_spaces() {
        WebElement label = driver.findElement(By.cssSelector(".todo-list li label"));
        String text = label.getText().trim();
        Assert.assertFalse(text.isEmpty());

    }

    @When("I quickly click the delete button on each todo")
    public void i_quickly_click_delete_on_each_todo() {
        List<WebElement> destroyButtons = driver.findElements(By.cssSelector(".destroy"));
        for (WebElement btn : destroyButtons) {
            // Hover to make destroy button visible
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    @Then("all todos should be deleted without breaking the app")
    public void all_todos_should_be_deleted() {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        Assert.assertEquals(0, todos.size());

    }


    @When("I click the {string} filter with no completed todos")
    public void i_click_the_filter(String filter) {
        List<WebElement> filters = driver.findElements(By.cssSelector(".filters li a"));
        for (WebElement link : filters) {
            if (link.getText().equalsIgnoreCase(filter)) {
                link.click();
                break;
            }
        }
    }

    @Then("I should see an empty list and no errors")
    public void i_should_see_empty_list_no_errors() {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        Assert.assertTrue(todos.isEmpty());

    }

    @When("I reload the page")
    public void i_reload_the_page() {
        driver.navigate().refresh();
    }

    @Then("the todo should still be present")
    public void the_todo_should_still_be_present() {
        List<WebElement> todos = driver.findElements(By.cssSelector(".todo-list li"));
        Assert.assertTrue(todos.size() > 0);

    }

    @Given("I open the TodoMVC React app with date support")
    public void i_open_the_TodoMVC_React_app_with_date_support() {
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/react/dist/");  // Adjust URL if you have custom app
        driver.manage().window().maximize();
        todoInput = driver.findElement(By.className("new-todo"));  // Selector for todo input field
    }

    @When("I enter {string} as the todo")
    public void i_enter_todo_item(String todo) {
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        todoInput.sendKeys(todo);  // Enter the todo item (including the date)
    }

    @When("I press Enter")
    public void i_press_enter() {
        WebElement todoInput = driver.findElement(By.id("todo-input"));
        todoInput.sendKeys(Keys.ENTER);  // Press Enter to submit the todo item
    }

}

