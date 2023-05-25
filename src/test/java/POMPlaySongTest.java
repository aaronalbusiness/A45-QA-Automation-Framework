import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;
import pom.AllSongsPage;

public class POMPlaySongTest extends BaseTest {
    @Test
    public void playSongTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        AllSongsPage allSongsPage = new AllSongsPage(getDriver()); // Correct variable declaration and assignment

        loginPage.login();
        homePage.chooseAllSongList(); // Correct variable name
        allSongsPage.contextClickFirstSong(); // Correct variable name
//      allSongsPage.playSong(); // Correct variable name
        allSongsPage.clickPlayOption();
        Assert.assertTrue(allSongsPage.isSongPlaying()); // Correct variable name

    }
}
