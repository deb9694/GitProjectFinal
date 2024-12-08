package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.LogUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    public static Properties prop;

    // Load config properties
    static {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            LogUtil.error("Failed to load config.properties");
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    // Retrieve WebDriver instance
    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    // Initialize WebDriver based on browser property
    private void initializeDriver() {
        String browser = prop.getProperty("browser").toLowerCase();
        try {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    threadLocalDriver.set(new ChromeDriver());
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless", "--disable-gpu");
                    threadLocalDriver.set(new ChromeDriver(chromeOptions));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    threadLocalDriver.set(new EdgeDriver());
                    break;
                case "edge-headless":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless");
                    threadLocalDriver.set(new EdgeDriver(edgeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    threadLocalDriver.set(new FirefoxDriver());
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    threadLocalDriver.set(new FirefoxDriver(firefoxOptions));
                    break;
                default:
                    throw new IllegalArgumentException("Browser \"" + browser + "\" not supported.");
            }
            LogUtil.info("Initialized WebDriver for browser: " + browser);
        } catch (Exception e) {
            LogUtil.error("Failed to initialize WebDriver");
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }


    public void setUp() {
        initializeDriver();
        WebDriver driver = getDriver();

        if (driver == null) {
            throw new RuntimeException("WebDriver initialization failed. Driver is null.");
        }

        driver.manage().window().maximize();
        String url = prop.getProperty("url");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL not specified in config.properties.");
        }
        driver.get(url);
        LogUtil.info("Navigated to URL: " + url);
    }

    @AfterClass
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            threadLocalDriver.remove(); // Clean up the ThreadLocal variable
            LogUtil.info("Browser closed and WebDriver instance cleared.");
        }
    }


}


