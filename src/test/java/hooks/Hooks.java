package hooks;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DriverManager;
import utils.ExtentManager;

public class Hooks {
    public static ExtentReports extent = ExtentManager.getInstance();
    public static ExtentTest test;
    @BeforeAll
    public static void globalSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        // start a new test in the report
        test = extent.createTest(scenario.getName());
    }

    @Before
    public void setUp() {
        if (DriverManager.getDriver() == null) {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            DriverManager.setDriver(driver);
        }
    }

    @Before
    public void setUp(Scenario scenario) {
        extent = ExtentManager.getInstance();
        test = extent.createTest(scenario.getName());
    }
    @AfterStep
    public void afterStep(Scenario scenario) {
        // you can log each step here if you want
         test.log(Status.PASS, scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Scenario Failed: " + scenario.getName());
        } else {
            test.pass("Scenario Passed: " + scenario.getName());
        }
        extent.flush(); // flushes after every scenario (or move to @AfterClass)
    }


    @After
    public void afterScenario(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver(); // get current driver

        if (scenario.isFailed()) {
            if (driver instanceof TakesScreenshot) {
                try {
                    byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

                    // Attach to Cucumber report
                    scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");

                    // Attach to Extent Report
                    test.fail("Scenario Failed",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

                } catch (Exception e) {
                    test.warning("Failed to capture screenshot: " + e.getMessage());
                }
            } else {
                test.warning("Driver does not support screenshots.");
            }
        } else {
            test.pass("Scenario Passed");
        }

        extent.flush();
    }

     @AfterAll
        public static void globalTearDown () {
            DriverManager.quitDriver();
        }
    }