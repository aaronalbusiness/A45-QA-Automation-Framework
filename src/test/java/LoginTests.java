import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends BaseTest {

    @Test

    // Defines a method named "loginInvalidEmailValidPasssword"
    public void loginInvalidEmailValidPasssword() {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Creates a new instance of the ChromeDriver class, passing in the "options" object, and assigns it to the variable "driver"
        WebDriver driver = new ChromeDriver(options);
        // Configures the driver to wait up to 10 seconds for elements to appear on the page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://bbb.testpro.io/";
        driver.get(url);

        // Finds the email input field on the page and assigns it to the variable "emailField"
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        // Simulates a click on the email input field (not needed)
        emailField.click();
        // Clears the contents of the email input field
        emailField.clear();
        // Enters the text "Invaslid@class.com" into the email input field
        emailField.sendKeys("Invaslid@class.com");

        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("te$t$tudent");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Uses the TestNG Assert class to verify that the current URL of the driver matches the value of the "url" variable
        Assert.assertEquals(driver.getCurrentUrl(), url);

        driver.quit();
    }

    @Test
    public void successfulLogin() {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://bbb.testpro.io/";
        driver.get(url);

        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.click(); //not needed
        emailField.clear();
        emailField.sendKeys("demo@class.com");

        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.click(); //not needed
        passwordField.clear();
        passwordField.sendKeys("te$t$tudent");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        //WebElement avatarIcon = driver.findElement(By.cssSelector()xpath("[alt= 'Avatar of student']"));
        WebElement avatarImage = driver.findElement(By.cssSelector("img.avatar"));
        Assert.assertTrue(avatarImage.isDisplayed());

        driver.quit();
    }
    @Test
    public static void loginEmptyEmailPasswordTest() {

//      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://bbb.testpro.io/";
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
        driver.quit();
    }
}
