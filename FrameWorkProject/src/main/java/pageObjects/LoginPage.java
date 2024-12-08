package pageObjects;


import base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    // PageFactory locators
    @FindBy(xpath = "//input[@id='login_field']")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement loginButton;

    // Constructor
    public LoginPage() {
        // Initialize PageFactory elements
        PageFactory.initElements(getDriver(), this);
    }

    // Login method
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
