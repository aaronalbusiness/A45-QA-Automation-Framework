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
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
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
        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit']"));
        loginButton.click();
    }
    public void searchSong (String songTitleKeyword) throws InterruptedException {
        WebElement searchField = driver.findElement(By.cssSelector("div#searchForm input[type=search]"));
        searchField.sendKeys(songTitleKeyword);
        Thread.sleep(2000);
    }
    public void clickViewAllBtn () throws InterruptedException {
        //WebElement viewAllSearchResults = driver.findElement(By.cssSelector("div.results section.songs h1"));
        WebElement viewAllSearchResults = driver.findElement(By.cssSelector("button[data-test='view-all-songs-btn'][rounded][small][orange]"));
        viewAllSearchResults.click();
        Thread.sleep(2000);

    }

    public void selectFirstSongResult () throws InterruptedException {
        WebElement firstSongResult = driver.findElement(By.cssSelector("section#songResultsWrapper tr.song-item td.title"));
        firstSongResult.click();
        Thread.sleep(2000);
    }

    public void clickAddToBtn () throws InterruptedException {
        WebElement addToButton = driver.findElement(By.cssSelector("button.btn-add-to"));
        addToButton.click();
        Thread.sleep(2000);
    }

    public void choosePlaylist () throws InterruptedException {
        WebElement choosePlaylist = driver.findElement(By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), 'Alan Test Playlist')]"));
        choosePlaylist.click();
        Thread.sleep(2000);
    }

    public String getNotificationText () {
        WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMessage.getText();

    }

}




