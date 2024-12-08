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

public class IssueTrackingTest extends TestBase {

    /*Test Data Table */
    final String repoName="TestData00";
    final String issueData="TestIssueData001";
    final String issueBody="TEstIssueBody002";


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
    public void navigateToRepo() throws InterruptedException {

        //Login to GitHub
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        Util.waitForElement(dashboardPage.hamBurgerMenu,9);
        TestListener.logStep("Able to Log in Sucessfully");

        dashboardPage.selectRepo(repoName);
        TestListener.logStep("Repo is selected Sucessfully");

    }

    @Test (priority= 2)
    public void issuesTabValidation() throws InterruptedException {

        Util.waitAndClick(dashboardPage.issuesTab,9);
        Util.waitAndClick(dashboardPage.newIssues,9);
        Util.waitToLoad(9);

        dashboardPage.titleIssue.sendKeys(issueData);
        dashboardPage.issue_body.sendKeys(issueBody);
        dashboardPage.submitIssue.click();
        TestListener.logStep("Issue Submitted Sucessfully");
    }


}
