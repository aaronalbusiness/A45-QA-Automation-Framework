import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;


public class BaseTest {

    public static WebDriver driver = null;

    public static String url = null;

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    public static void launchBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        url = "https://bbb.testpro.io/";
        driver.get(url);
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }

    protected static void navigateToPage() {
        String url = "http://bbb.testpro.io";
        driver.get(url);

    }


    public static void login(String email, String password)  {
        provideEmail(email);
        providePassword(password);
        clickSubmit();


    }
    public static void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("[type = 'password']"));
        passwordField.clear();
        passwordField.sendKeys(password);

    }

    public static void clickSubmit()  {
        WebElement submitButton = driver.findElement(By.cssSelector("[type = 'submit']"));
        submitButton.click();
        //  thread.sleep(2000);


    }
    public static void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("[type = 'email']"));
        emailField.clear();
        emailField.sendKeys(email);

    }
}


