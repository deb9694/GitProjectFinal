package utils;

import base.TestBase;
import listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Util extends TestBase {

    public static void waitForElement(WebElement element, int timeoutInSeconds) {


        try {
            // Create WebDriverWait instance
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));

            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(element));

            TestListener.logStep("Element found  successfully!");
        } catch (Exception e) {
            TestListener.logStep("Failed to find Element: " + e.getMessage());
        }
    }

    public static void waitAndClick(WebElement element, int timeoutInSeconds) {


        try {
            // Create WebDriverWait instance
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));

            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(element));

            // Click the element
            element.click();
            TestListener.logStep("Element clicked successfully!");
        } catch (Exception e) {
            TestListener.logStep("Failed to click the element: " + e.getMessage());
        }
    }

    public static void waitAndClick(String xpath, int timeoutInSeconds) {
        try {
            WebDriver driver = getDriver(); // Replace with your WebDriver instance provider

            // Create WebDriverWait instance
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

            // Wait until the element located by XPath is clickable
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            // Click the element
            element.click();
            TestListener.logStep("Element located by XPath [" + xpath + "] clicked successfully!");
        } catch (Exception e) {
            TestListener.logStep("Failed to click the element located by XPath [" + xpath + "]: " + e.getMessage());
        }
    }

    public static void waitToLoad(int k) throws InterruptedException {
        Thread.sleep(2000);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(k));
    }

    public static void moveToElementAndClick(WebElement element) {
        try {
            // Create Actions instance
            Actions actions = new Actions(getDriver());

            // Move to the element and click
            actions.moveToElement(element).click().perform();

            TestListener.logStep("Moved to element and clicked successfully!");
        } catch (Exception e) {
            TestListener.logStep("Failed to move to element and click: " + e.getMessage());
        }
    }

}
