import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;

public class PlaylistPOMTest extends BaseTest {

    @Test
    public void renamePlaylist() {
        // Prerequisite - at least one user-created playlist
        String playlistName = "Test Pro Playlist";

        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login();
        homePage.doubleClickPlaylist();
        homePage.enterNewPlaylistName(playlistName);

        Assert.assertTrue(homePage.doesPlaylistExist(playlistName));
    }
}
