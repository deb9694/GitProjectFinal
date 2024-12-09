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
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));

            wait.until(ExpectedConditions.elementToBeClickable(element));

            TestListener.logStep("Element found  successfully!");
        } catch (Exception e) {
            TestListener.logStep("Failed to find Element: " + e.getMessage());
        }
    }

    public static void waitAndClick(WebElement element, int timeoutInSeconds) {


        try {

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));

            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            TestListener.logStep("Element clicked successfully!");
        } catch (Exception e) {
            TestListener.logStep("Failed to click the element: " + e.getMessage());
        }
    }

    public static void waitAndClick(String xpath, int timeoutInSeconds) {
        try {
            WebDriver driver = getDriver(); 

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

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
            Actions actions = new Actions(getDriver());

            actions.moveToElement(element).click().perform();

            TestListener.logStep("Moved to element and clicked successfully!");
        } catch (Exception e) {
            TestListener.logStep("Failed to move to element and click: " + e.getMessage());
        }
    }

}
