import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;
import pom.AllSongsPage; // Import the AllSongsPage class

public class PlaySongPOMTest extends BaseTest {
    @Test
    public void playSongTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage allSongsPage = new AllSongsPage(driver); // Correct variable declaration and assignment

        loginPage.login();
        homePage.chooseAllSongList(); // Correct variable name
        allSongsPage.contextClickFirstSong(); // Correct variable name
        allSongsPage.playSong(); // Correct variable name
        Assert.assertTrue(allSongsPage.isSongPlaying()); // Correct variable name
    }
}
