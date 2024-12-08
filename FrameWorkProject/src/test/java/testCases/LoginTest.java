package testCases;

import base.TestBase;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;

public class LoginTest extends TestBase {

    WebDriver driver;

    @BeforeClass
    private void setingUp(){
        setUp();
        driver=getDriver();
    }

    @Test
    public void testValidLogin() {
        TestListener.logStep("Starting login test...");

        String expectedTitle = "GitHub";
        String actualTitle = driver.getTitle();
        TestListener.logStep("Retrieved page title: " + actualTitle);

        Assert.assertTrue(actualTitle.contains(expectedTitle), "Login failed!");
        TestListener.logStep("Login test passed. Title contains: " + expectedTitle);

        LoginPage loginPage= new LoginPage();
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));

        DashboardPage dashboardPage=new DashboardPage();
        Assert.assertTrue(dashboardPage.hamBurgerMenu.isDisplayed());
        TestListener.logStep("Able to Log in Sucessfully");
    }
}
