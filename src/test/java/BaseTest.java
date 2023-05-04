import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;


public class BaseTest {

    static WebDriverWait wait;

    public static WebDriver driver= null;

    public static String url = "";

   //public static WebDriverWait wait;


    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseURL"})

    public void launchBrowser(String BaseURL) {
        // Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        url = BaseURL;
        navigateToPage();


    }


    @AfterMethod
    public void tearDownBrowser() {driver.quit();
    }



    // Methods to initioally open page and login
   public void navigateToPage() {driver.get(url);

    }
    public void provideEmail(String email)  {
        //WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='email']")));
        emailField.click(); //not needed
        emailField.clear();
        emailField.sendKeys(email);
    }
    public static void providePassword(String password) {
        //WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']")));
        passwordField.click(); //not needed
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmit()  {
        //WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        loginButton.click();
    }

    public void login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickSubmit();
    }




    // Methods for adding song to playlist

    public void searchSong (String songTitleKeyword) throws InterruptedException {
        //WebElement searchField = driver.findElement(By.cssSelector("div#searchForm input[type=search]"));
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#searchForm input[type=search]")));
        searchField.sendKeys(songTitleKeyword);
        Thread.sleep(2000);
    }
    public void clickViewAllBtn () throws InterruptedException {
        WebElement viewAllSearchResults = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='view-all-songs-btn'][rounded][small][orange]")));
        viewAllSearchResults.click();
        Thread.sleep(2000);
    }

    public void selectFirstSongResult () throws InterruptedException {
        WebElement firstSongResult = wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("section#songResultsWrapper tr.song-item td.title"))));
        firstSongResult.click();
        Thread.sleep(2000);
    }

    public void clickAddToBtn () throws InterruptedException {
        WebElement addToButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-add-to")));
        addToButton.click();
        Thread.sleep(2000);
    }

    public void choosePlaylist () throws InterruptedException {
        //WebElement choosePlaylist = driver.findElement(By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), 'temp12')]"));
        WebElement choosePlaylist = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), 'temp12')]"))));
        choosePlaylist.click();
        Thread.sleep(2000);
    }

    public String getNotificationText () {
        //WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        WebElement notificationMessage = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.success.show")));
        return notificationMessage.getText();

    }





    public static void clickSaveButton() {
        //WebElement saveButton = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        saveButton.click();
    }

    public static void provideProfileName(String randomName) {
        //WebElement profileName = driver.findElement(By.cssSelector("[name='name']"));
        WebElement profileName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='name']")));
        profileName.clear();
        profileName.sendKeys(randomName);
    }
    public static void provideCurrentPassword(String password) {
        //WebElement currentPassword = driver.findElement(By.cssSelector("[name='current_password']"));
        WebElement currentPassword = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='current_password']")));
        currentPassword.clear();
        currentPassword.sendKeys(password);
    }


    public static void clickAvatarIcon() {

        WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
    }

}
