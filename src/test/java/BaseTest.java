import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.edge.EdgeDriver;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


public class BaseTest {

    public static WebDriver driver= null;
    public static WebDriverWait wait = null;
    public static ThreadLocal<WebDriver> threadDriver= null;
    public static Actions actions = null;
    public static String url = "";


    String newPlaylistName = "Test Pro edited Playlist";

    public static WebDriver getDriver() {

        return threadDriver.get();
    }

    @BeforeSuite
    static void setupClass() {
    }

    @BeforeMethod
    @Parameters({"BaseURL"})

    public void launchBrowser(String BaseURL) throws MalformedURLException {

        threadDriver = new ThreadLocal<>();
        driver = pickBrowser(System.getProperty("browser"));
        threadDriver.set(driver);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        actions = new Actions(getDriver());
        url = BaseURL;
        navigateToPage();
    }

    @AfterMethod //(enabled = false)
    public void tearDownBrowser() {
        getDriver().quit();
        threadDriver.remove();
    }

    private static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://10.0.0.153:4444/";//replaced with your grid url
        //java -jar selenium-server-4.9.0.jar standalone

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments("--disable-notifications");
                return new EdgeDriver(edgeOptions);
            case "safari":
                WebDriverManager.safaridriver().setup();//replaced with safari.setup
                return new SafariDriver();

            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-safari":
                caps.setCapability("browserName", "safari");
                return new RemoteWebDriver(URI.create(gridURL).toURL(), caps);

            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                return driver = new ChromeDriver(options);
        }
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
    public boolean doesPlaylistExist( ) {
        // By newPlaylist = By.xpath("//a[text()='"+playlistName+"']");
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+newPlaylistName+"']")));
        return playlistElement.isDisplayed();
    }

    public void choosePlaylist () throws InterruptedException {
        //By choosePlaylistLocator = By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), 'MyPlaylist')]");
        WebElement playlistElement = driver.findElement(By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), '"+newPlaylistName+"')]"));
        playlistElement.click();
        Thread.sleep(2000);
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

        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        playlistInputField.sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.BACK_SPACE));//we use Keys.COMMAND for mac to select all then backspace to delete the current playlist name and replace with new name
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
        WebElement soundBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid='sound-bar-play']")));
        return soundBar.isDisplayed();
    }

    public String getNotificationText () {
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


