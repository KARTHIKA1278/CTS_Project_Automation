package runner;  // Ensure this matches the package structure of your project


import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;


import java.io.File;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs","hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Test
    public void runCucumber() {
    }
}
