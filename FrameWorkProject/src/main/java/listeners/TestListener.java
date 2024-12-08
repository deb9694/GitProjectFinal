package listeners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;
import utils.LogUtil;

public class TestListener implements ITestListener {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Start a new test in Extent Reports
        ExtentTest test = ExtentReportManager.startTest(result.getName());
        extentTest.set(test);

        LogUtil.info("Test started: " + result.getName());
        test.info("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success in Extent Reports
        ExtentTest test = extentTest.get();
        test.pass("Test passed: " + result.getName());
        LogUtil.info("Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure in Extent Reports
        ExtentTest test = extentTest.get();
        test.fail("Test failed: " + result.getName());
        test.fail(result.getThrowable()); // Capture exception in the report

        LogUtil.error("Test failed: " + result.getName());
        LogUtil.error("Exception: " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log skipped tests in Extent Reports
        ExtentTest test = extentTest.get();
        test.skip("Test skipped: " + result.getName());
        test.skip(result.getThrowable()); // Capture exception (if any)

        LogUtil.warn("Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Log tests that partially succeed
        ExtentTest test = extentTest.get();
        test.warning("Test partially succeeded: " + result.getName());
        LogUtil.warn("Test partially succeeded: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        LogUtil.info("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtil.info("Test suite finished: " + context.getName());
        ExtentReportManager.endReport(); // Flush the Extent report
    }

    /**
     * Add a custom step logger for each test step.
     * This can be called dynamically during test execution.
     */
    public static void logStep(String message) {
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.info(message); // Log in Extent Report
        }
        LogUtil.info(message); // Log using Log4j
    }
}
