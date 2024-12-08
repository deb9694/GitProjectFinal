package testCases;

import base.TestBase;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.Util;

public class PullRequestWorkflow extends TestBase {

    /*Test Data Table */
    final String repoName="TestData004";


    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeClass
    private void setingUp(){
        setUp();
        driver=getDriver();

        loginPage= new LoginPage();
        dashboardPage=new DashboardPage();
    }

    @Test (priority= 1)
    public void navigateToPullRequests() throws InterruptedException {

        //Login to GitHub
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertTrue(dashboardPage.hamBurgerMenu.isDisplayed());
        TestListener.logStep("Able to Log in Sucessfully");

        Util.waitAndClick(dashboardPage.pullRequests,9);
        TestListener.logStep("Navigate Sucessfully");
    }

    @Test (priority= 2)
    public void createNewPullRequest() throws InterruptedException {

        Util.waitAndClick(dashboardPage.newPR,9);

        TestListener.logStep("Navigate Sucessfully");

    }
}
