package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;

public class ExtentReportManager {

    private static ExtentReports extent;

    // Initialize the ExtentReports instance and set up the HTML reporter
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File("test-output/extent-report.html"));
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    // Start a new test and create an ExtentTest instance
    public static ExtentTest startTest(String testName) {
        return getInstance().createTest(testName);  // Return ExtentTest instance
    }

    // Log the test steps
    public static void logInfo(ExtentTest test, String message) {
        test.info(message);
    }

    // Log when the test passes
    public static void logPass(ExtentTest test, String message) {
        test.pass(message);
    }

    // Log when the test fails
    public static void logFail(ExtentTest test, String message) {
        test.fail(message);
    }

    // Finalize the report after all tests
    public static void endReport() {
        extent.flush();
    }
}
