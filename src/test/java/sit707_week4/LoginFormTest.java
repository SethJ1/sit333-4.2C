package sit707_week4;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginFormTest {

    private WebDriver driver;

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Before
    public void setUp() {
        // Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bunnings.com.au/login");
    }

    @Test
    public void testEmptyUsernameAndPassword() {
        // Enter empty username
        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        usernameField.sendKeys("");

        // Enter empty password
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));
        passwordField.sendKeys("");

        // Click sign-in button
        WebElement signInButton = driver.findElement(By.id("okta-signin-submit"));
        signInButton.click();

     // Verify login failure by checking if the username field is still present
        assertTrue(driver.findElements(By.id("okta-signin-username")).size() > 0);
    }

    @Test
    public void testInvalidUsernameAndValidPassword() {
        // Enter invalid username
        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        usernameField.sendKeys("invalid_username");

        // Enter valid password
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));
        passwordField.sendKeys("Password1!");

        // Click sign-in button
        WebElement signInButton = driver.findElement(By.id("okta-signin-submit"));
        signInButton.click();

        // Verify login failure by checking if the username field is still present
        assertTrue(driver.findElements(By.id("okta-signin-username")).size() > 0);
    }

    @Test
    public void testValidUsernameAndInvalidPassword() {
        // Enter valid username
        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        usernameField.sendKeys("sethj4774@gmail.com");

        // Enter invalid password
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));
        passwordField.sendKeys("invalidPassword123");

        // Click sign-in button
        WebElement signInButton = driver.findElement(By.id("okta-signin-submit"));
        signInButton.click();

        // Verify login failure by checking if the username field is still present
        assertTrue(driver.findElements(By.id("okta-signin-username")).size() > 0);
    }

    @Test
    public void testValidUsernameAndValidPassword() {
        // Enter valid username
        WebElement usernameField = driver.findElement(By.id("okta-signin-username"));
        usernameField.sendKeys("sethj4774@gmail.com");

        // Enter valid password
        WebElement passwordField = driver.findElement(By.id("okta-signin-password"));
        passwordField.sendKeys("Password1!");

        // Click sign-in button
        WebElement signInButton = driver.findElement(By.id("okta-signin-submit"));
        signInButton.click();
        
        // Verify login success by checking if the username for search bar
        assertTrue(driver.findElements(By.id("custom-css-outlined-input")).size() > 0);
    }



    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
