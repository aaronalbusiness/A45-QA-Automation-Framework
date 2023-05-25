package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import pagefactory.HomePage;
import pagefactory.LoginPage;
import java.net.MalformedURLException;
import java.time.Duration;



public class LoginStepDefinitions {

    WebDriver driver;

    WebDriverWait wait;

    String url = "https://bbb.testpro.io/";

    @Before
    public void launchBrowser() {

      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--remote-allow-origins=*");
      options.addArguments("--disable-notifications");
      driver = new ChromeDriver(options);
      wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      driver.get("https://bbb.testpro.io/");

    }

    @After
    public void tearDownBrowser() {

        driver.quit();
    }

    @Given("I open login page")
    public void iOpenLoginPage() {
    }

    @When("I enter email {string}")
    public void iEnterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']"))).sendKeys(email);

    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']"))).sendKeys(password);
    }

    @And("I submit")
    public void iSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))).click();
    }

    @Then("I am logged in")
    public void iAmLoggedIn() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());//removed extra ' from locator

    }

    @Then("I am not logged in")
    public void iAmNotLoggedIn() {
//        Assert.assertEquals(getDriver().getCurrentUrl(), url);
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }


    // Using Page objects
//    @When("I enter email {string}")
//    public void iEnterEmail(String email) {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.provideEmail(email);
//    }
//
//    @And("I enter password {string}")
//    public void iEnterPassword(String password) {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.provideEmail(password);
//    }
//
//    @And("I submit")
//    public void iSubmit() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.clickSubmit();
//    }

}
