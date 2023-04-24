import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;


public class BaseTest {
    WebDriver driver;

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    public void setupBrowser() {
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDownBrowser() {
        driver.quit();
    }

    public void openLoginUrl() {
        String url = "http://bbb.testpro.io";
        driver.get(url);
    }
    public void enterEmail(String email)  {
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.sendKeys(email);

    }
    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.cssSelector("[type='password']"));
        passwordField.sendKeys(password);
    }

    public void clickSubmit()  {
        WebElement loginButton = driver.findElement(By.cssSelector("[type='summit']"));
        loginButton.click();
    }
    public void searchSong (String songTitleKeyword) throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector("???[name ='q']"));
        searchField.sendKeys(songTitleKeyword);
        Thread.sleep(2000);
    }
    public void clickViewAllBtn () throws InterruptedException {
        WebElement viewAllSearchResults = driver.findElement(By.xpath(?));
        viewAllSearchResults.click();
        Thread.sleep(2000);
    }

    public void selectFirstSongResult () throws InterruptedException {
        WebElement firstSong = driver.findElement(By.cssSelector( ?));
        firstSong.click();
        Thread.sleep(2000);
    }

    public void clickAddToBtn () throws InterruptedException {
        WebElement addToButton = driver.findElement(By.cssSelector( ?));
        addToButton.click();
        Thread.sleep(2000);
    }

    public void choosePlaylist () throws InterruptedException {
        WebElement choosePlaylist = driver.findElement.ByXPath( ?));
        choosePlaylist.click();
        Thread.sleep(2000);

    }

    public String getNotificationText () {
        WebElement notificationMessage = driver.findElement(By.cssSelector());
        return notificationMessage.getText();


    }



}




