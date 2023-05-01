import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.time.Duration;


public class BaseTest {


    public static WebDriver driver= null;

    public static String url = "";


    public void navigateToPage() {driver.get(url);

    }
    public void provideEmail(String email)  {
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.click(); //not needed
        emailField.clear();
        emailField.sendKeys(email);

    }
    public static void providePassword(String password) {
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

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    @Parameters({"BaseURL"})

    public void setupBrowser(String BaseURL) {
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        url = BaseURL;
        navigateToPage();
    }

    @AfterMethod
    public void tearDownBrowser() {driver.quit();
    }


}
