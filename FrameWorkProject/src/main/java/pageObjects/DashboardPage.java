package pageObjects;


import base.TestBase;
import com.aventstack.extentreports.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Util;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends TestBase {
    // Constructor
    public DashboardPage() {
        // Initialize PageFactory elements
        PageFactory.initElements(getDriver(), this);
    }
    // PageFactory locators
    @FindBy(xpath = "//span[.='New']")
    public WebElement newButton;

    @FindBy(xpath = "//div[.='Repository name']")
    public WebElement repositoryNameField;

    @FindBy(xpath = "//label[.='Public']/../preceding-sibling::div")
    public WebElement publicCheck;

    @FindBy(xpath = "//label[.='Private']")
    public WebElement privateCheck;

    @FindBy(xpath = "//button[@id='global-create-menu-anchor']")
    public WebElement plusIcon;

    @FindBy(xpath = "(//span[text()='New repository'])[last()]")
    public WebElement newRepoFromDropDown;

    @FindBy(xpath = "//input[@data-testid='repository-name-input']")
    public WebElement newRepoInptField;

    @FindBy(xpath = "//span[.='Create repository']")
    public WebElement createRepoButton;

    @FindBy(xpath = "//span[@id='RepoNameInput-is-available']")
    public WebElement availableStatus;

    @FindBy(xpath = "//button")
    public WebElement hamBurgerMenu;

    @FindBy(xpath = "//span[@data-content='Issues']")
    public WebElement issuesTab;

    @FindBy(xpath = "//span[.='New issue']")
    public WebElement newIssues;

    @FindBy(xpath = "//input[@placeholder='Title']")
    public WebElement titleIssue;

    @FindBy(xpath = "//textarea[@id='issue_body']")
    public WebElement issue_body;

    @FindBy(xpath = "//button[@type='submit' and contains(.,' Submit new issue')]")
    public WebElement submitIssue;

    @FindBy(xpath = "//span[@data-content='Pull requests']")
    public WebElement pullRequests;

    @FindBy(xpath = "//span[.='New pull request']")
    public WebElement newPR;

    public void navigateToTabsAndPrintTexts(){
        List<WebElement> tabList=getDriver().findElements(By.xpath("//li[@class='d-inline-flex']//span[@data-content]"));
        for(WebElement tab: tabList){
            tab.click();
        }

    }


    public void selectRepo(String repoName) {
        hamBurgerMenu.click();
        Util.waitAndClick("//span[contains(.,'/"+repoName+"') and @class='ActionListItem-label ActionListItem-label--truncate']",9);
    }


    // Login method
    public void selectNewRepoFromDropdown() {
        plusIcon.click();
        Util.waitAndClick(newRepoFromDropDown,9);
    }

    public void createNewRepo(String repoName, String accessSpec) throws InterruptedException {

        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//label[.='"+accessSpec+"']")).click();
        Util.waitToLoad(9);

        try {
            newRepoInptField.sendKeys(repoName);
            Util.waitAndClick(availableStatus,9);
        }
        catch (StaleElementReferenceException e){
            getDriver().findElement(By.xpath("//input[@data-testid='repository-name-input']")).sendKeys(repoName);
            Util.waitAndClick(availableStatus,9);
        }

        try{
            createRepoButton.click();
            Util.waitToLoad(9);
        }
        catch (StaleElementReferenceException e){
            getDriver().findElement(By.xpath("//span[.='Create repository']")).click();
            Util.waitToLoad(9);
        }
    }

}
