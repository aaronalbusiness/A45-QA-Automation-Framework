package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    private By firstPlaylist = By.cssSelector(".playlist:nth-child(3)");
    private By playlistNameField = By.cssSelector("[name='name']");
    private By allSongsPage = By.xpath("//a[contains(text(),'All Songs')]");

    String newPlaylistName = "MyPlaylist";


    //  SEARCH SONGS

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

    public void choosePlaylist () throws InterruptedException {
        //By choosePlaylistLocator = By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), 'MyPlaylist')]");
        WebElement playlistElement = driver.findElement(By.xpath("//section[@id = 'songResultsWrapper']//li[contains(text(), '"+newPlaylistName+"')]"));
        playlistElement.click();
        Thread.sleep(2000);
    }



    //  YOUR MUSIC/ All Songs
    public void chooseAllSongList() {
        click(allSongsPage);
    }




    // PLAYLISTS
    public void doubleClickPlaylist() {
        doubleClick(firstPlaylist);
    }

    public void enterNewPlaylistName(String playlistName) {
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.BACK_SPACE));
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.ENTER);
    }

    public boolean doesPlaylistExist(String playlistName) {
        By newPlaylist = By.xpath("//a[text()='" + playlistName + "']");
        return findElement(newPlaylist).isDisplayed();
    }

    public void clickDeletePlaylistBtn() {
        WebElement deletePlaylist = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-delete-playlist")));
        deletePlaylist.click();

    }

    public void openPlaylist() {
        WebElement emptyPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        emptyPlaylist.click();
    }

}
