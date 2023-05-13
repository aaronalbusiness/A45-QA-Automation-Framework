import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;

public class PlaylistPOMTest extends BaseTest {

    @Test
    public void renamePlaylist() {
        // Prerequisite - at least one user-created playlist
        String playlistName = "Test Pro Playlist";

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login();
        homePage.doubleClickPlay1ist();
        homePage.enterNewPlaylistName(playlistName);

        Assert.assertTrue(homePage.doesPlaylistExist(playlistName));
    }
}
