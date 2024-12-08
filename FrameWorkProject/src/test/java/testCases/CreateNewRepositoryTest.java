package testCases;

import base.TestBase;
import listeners.TestListener;
import org.apache.logging.log4j.core.net.Priority;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.Util;

public class CreateNewRepositoryTest extends TestBase {

    /*Test Data Table */
    final String repoName="TestData007";


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
    public void fieldValidation() throws InterruptedException {

        //Login to GitHub
        loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertTrue(dashboardPage.hamBurgerMenu.isDisplayed());
        TestListener.logStep("Able to Log in Sucessfully");

        dashboardPage.selectNewRepoFromDropdown();
        Util.waitForElement(dashboardPage.repositoryNameField,9);
        //Field Validation
        Assert.assertTrue(dashboardPage.repositoryNameField.isDisplayed());
        Assert.assertTrue(dashboardPage.publicCheck.isDisplayed());
        Assert.assertTrue(dashboardPage.privateCheck.isDisplayed());
        TestListener.logStep("All Fields Sucessfully Validated");
    }

    @Test (priority= 2)
    public void newRepoCreation() throws InterruptedException {
        dashboardPage.selectNewRepoFromDropdown();
        dashboardPage.createNewRepo(repoName,"Public");
        System.out.println(getDriver().getCurrentUrl());
        Assert.assertTrue(getDriver().getCurrentUrl().contains(repoName));
        System.out.println(getDriver().getCurrentUrl());
        TestListener.logStep("New Repo Created Sucessfully ");
    }
}
