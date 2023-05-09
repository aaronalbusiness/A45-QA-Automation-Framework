import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.List;
//import java.util.UUID;


public class BaseTest {

    public static WebDriver driver= null;

    public static WebDriverWait wait;

    public static Actions actions = null;

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
        actions = new Actions(driver);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        url = BaseURL;
        navigateToPage();
    }

//    @AfterMethod
//    public void tearDownBrowser() {
//        driver.quit();
//    }

    // Helper methods to open page and login
    public void navigateToPage() {driver.get(url);

    }
    public void login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickSubmit();
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
        //WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();
    }


    // Helper methods for adding song to playlist

    public void searchSong (String songTitleKeyword) throws InterruptedException {
        //WebElement searchField = driver.findElement(By.id("searchForm"));
        WebElement searchField = driver.findElement(By.cssSelector("input[type='search'][name='q']"));
        System.out.println("sF:"+searchField);
        searchField.click();
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
        WebElement choosePlaylist = driver.findElement(By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), 'MyPlaylist')]"));
        choosePlaylist.click();
        Thread.sleep(2000);
    }

    public String getNotificationText () {
        WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));
        return notificationMessage.getText();

    }


    // Helper Methods for Delete plylist testbbbbbbbb

    public void openPlaylist() {
        WebElement emptyPlaylist = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".playlist:nth-child(3)")));

        emptyPlaylist.click();
    }

    public void clickDeletePlaylistBtn()  {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-delete-playlist")));
        WebElement deletePlaylist = driver.findElement(By.cssSelector(".btn-delete-playlist"));
        // WebElement deletePlaylist = driver.findElement(By.cssSelector("button.del.btn-delete-playlist"));
        deletePlaylist.click();

    }

    public String getDeletedPlaylistMsg() {
        WebElement notificationsMsg = driver.findElement( By.cssSelector("div.success.show"));
        return notificationsMsg.getText();
    }



    //Action Methods

    //Action Methods - Context Click
    public void chooseAllSongList() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a.songs[href='#!/songs']"))); //"li a.songs"
        driver.findElement(By.cssSelector("a.songs[href='#!/songs']")).click();
    }
    public void contextClickFirstSong() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        WebElement firstSong = driver.findElement(By.cssSelector(".all-songs tr.song-item:nth-child(1)"));
        actions.contextClick(firstSong).perform();
    }


    //Action Methods - Hover
    public void hoverPlay() {
        WebElement play = driver.findElement(By.cssSelector("[data-testid='play-btn']"));
        actions.moveToElement(play).perform();
        //return driver.findElement(By.cssSelector(("[data-testid='play-btn']")));
    }


    //Action Methods - countSongsInPlaylist

    public void displayAllSongs(){
        chooseAllSongList();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".all-songs tr.song-item")));
        List<WebElement> songsList = driver.findElements(By.cssSelector(".all-songs tr.song-item"));
        Assert.assertEquals(songsList.size(), 63);
    }





//
//    public void doubleClickChoosePlaylist() {
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".playlist:nth-child(3)")));
//        WebElement playlist = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
//        actions.doubleClick(playlist).perform();
//    }
//
//
//    public void choosePlayOption() {
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("li.playback")));
//    }
//

//
//    public static void clickSaveButton() {
//        WebElement saveButton = driver.findElement(By.cssSelector("button[type='submit']"));
//        saveButton.click();
//    }
//
//    public static void provideProfileName(String randomName) {
//        WebElement profileName = driver.findElement(By.cssSelector("[name='name']"));
//        profileName.clear();
//        profileName.sendKeys(randomName);
//    }
//    public static void provideCurrentPassword(String password) {
//        WebElement currentPassword = driver.findElement(By.cssSelector("[name='current_password']"));
//        currentPassword.clear();
//        currentPassword.sendKeys(password);
//    }
//
//    //public static String generateRandomName() {return UUID.randomUUID().toString().replace();
//
//    public static void clickAvatarIcon() {
//        WebElement avatarIcon = driver.findElement(By.cssSelector("img.avatar"));
//    }
//
}
