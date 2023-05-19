package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    private By firstPlaylist = By.cssSelector(".playlist:nth-child(3)");
    private By playlistNameField = By.cssSelector("[name='name']");
    private By allSongsPage = By.xpath("//a[contains(text(),'All Songs')]");



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

    public void chooseAllSongList() {
        click(allSongsPage);
    }
}
