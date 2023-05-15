package pagefactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "img[class='avatar']")
    WebElement avatarIcon;

    @FindBy(css = ".playlist:nth-child(3)")
    WebElement firstPlaylist;

    @FindBy(css = "[name='name']")
    WebElement playListNameField;

    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }

    public HomePage doubleClickPlaylist() {
        doubleClick(firstPlaylist);
        return this;
    }

    public void enterNewPlaylistName(String playlistName) {
        findElement(playListNameField).sendKeys(playlistName);
        findElement(playListNameField).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.BACK_SPACE));//keys.command and a for mac to select all
        findElement(playListNameField).sendKeys(playlistName);
        findElement(playListNameField).sendKeys(Keys.ENTER);
    }

    public String getPlaylistName() {
        return findElement(firstPlaylist).getText();
    }

    public boolean IsAvatarDisplayed() {
        return findElement(avatarIcon).isDisplayed();//we use find element () helper method since it has the explicit wait already
    }
}
