import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;

public class POMPlaylistTest extends BaseTest {

    @Test(priority=1)
    public void deletePlaylist() {
        // This method only works if the playlist being selected to delete is empty
        String deletedPlaylistMsg = "Deleted playlist";

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login();
        homePage.openPlaylist();
        homePage.clickDeletePlaylistBtn();
        Assert.assertTrue(getDeletedPlaylistMsg().contains(deletedPlaylistMsg));
    }
    @Test(priority=2)
    public void renamePlaylist() {
        // Prerequisite - at least one user-created playlist
        String playlistName = "MyPlaylist";

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login();
        homePage.doubleClickPlaylist();
        homePage.enterNewPlaylistName(playlistName);

        Assert.assertTrue(homePage.doesPlaylistExist(playlistName));
    }

    @Test(priority=3)
    public void addSongToPlaylist() throws InterruptedException {
        // Must be a playlist named Test Pro edited Playlist and song being added can not be in playlist
        String newSongAddedNotificationText = "Added 1 song into";

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login();
        homePage.searchSong("Reactor");
        homePage.clickViewAllBtn();
        homePage.selectFirstSongResult();
        homePage.clickAddToBtn();
        homePage.choosePlaylist();

        Assert.assertTrue(getNotificationText().contains(newSongAddedNotificationText));
    }
}
