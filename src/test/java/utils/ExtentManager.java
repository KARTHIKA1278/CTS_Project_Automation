package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/extent-report.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            // Optional: Load config from XML (theme, report title, etc.)
          // spark.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml"));

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Optional info
            extent.setSystemInfo("Project Name", "TodoMVC App");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Your Name");
        }
        return extent;
    }
}
