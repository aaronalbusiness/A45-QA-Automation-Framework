import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
import org.openqa.selenium.Keys;


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

    @AfterMethod (enabled = false)
    public void tearDownBrowser() {
        driver.quit();
    }



    // Helper methods to open page and login
    public void navigateToPage() {
        driver.get(url);
    }
    public void login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickSubmit();
    }

    public void provideEmail(String email)  {
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='email']")));
        emailField.click(); //not needed
        emailField.clear();
        emailField.sendKeys(email);
    }
    public static void providePassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']")));
        passwordField.click(); //not needed
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public static void clickSubmit()  {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        loginButton.click();
    }




    // Helper methods for adding song to playlist
    public void searchSong (String songTitleKeyword) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='search'][name='q']")));
        System.out.println("sF:"+searchField);
        searchField.click();
        searchField.sendKeys(songTitleKeyword);
    }

    public void clickViewAllBtn () {
        WebElement viewAllSearchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-test='view-all-songs-btn'][rounded][small][orange]")));
        viewAllSearchResults.click();
    }

    public void selectFirstSongResult ()  {
        WebElement firstSongResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section#songResultsWrapper tr.song-item td.title")));
        firstSongResult.click();
    }

    public void clickAddToBtn () {
        WebElement addToButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-add-to")));
        addToButton.click();
    }

    public void choosePlaylist () {
 By choosePlaylistLocator = By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), 'MyPlaylist')]");
        WebElement choosePlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(choosePlaylistLocator));
        choosePlaylist.click();
    }


    // Helper Methods for Delete playlist

    public void openPlaylist() {
        WebElement emptyPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        emptyPlaylist.click();
    }

    public void clickDeletePlaylistBtn()  {
        WebElement deletePlaylist = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-delete-playlist")));
        deletePlaylist.click();
    }

    public String getDeletedPlaylistMsg() {
        WebElement notificationsMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
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

    public void doubleClickPlaylist() {
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
    }

    public void enterNewPlayListName() {
        String newPlaylistName = "Test Pro edited Playlist";
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public void playSong() {
        WebElement playNextButton = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));

        playNextButton.click();
        playButton.click();
    }

    public boolean isSongPlaying() {
        //WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));


        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='sound-bar-play']")));




        return soundBar.isDisplayed();
    }

    public String getNotificationText () {
        //WebElement notificationMessage = driver.findElement(By.cssSelector("div.success.show"));

        WebElement notificationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));


        return notificationMessage.getText();

    }



//
//    public boolean doesPlaylistExist(){
//        String newPlaylistName = "Test Pro edited Playlist";
//        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+newPlaylistName+"']")));
//        return playlistElement.isDisplayed();
//    }
//
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
