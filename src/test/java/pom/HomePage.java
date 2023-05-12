package pom;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {


    public HomePage(WebDriver givenDriver) {super(givenDriver); }
    By firstPlaylist = By.cssSelector(".playlist:nth-child(3)");

    By playlistNameField = By.cssSelector("[name='name']");

    public void doubleClickPlay1ist() { doubleClick(firstPlaylist); }

    public void enterNewPlaylistName(String playlistName) {
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.BACK_SPACE));//keys.command and a for mac to select all
        findElement(playlistNameField).sendKeys(playlistName);
        findElement(playlistNameField).sendKeys(Keys.ENTER);
    }

    public boolean doesPlaylistExist(String playlistName) {
        By newPlaylist = By.xpath("//a[text()='"+playlistName+"']");
        return findElement(newPlaylist).isDisplayed();
    }
}
