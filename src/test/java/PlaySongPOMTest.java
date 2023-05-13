import org.testng.Assert;
import org.testng.annotations.Test;
import pom.HomePage;
import pom.LoginPage;


public class PlaySongPOMTest extends BaseTest{

    //private WebDriver driver;

    @Test
    public void playSongTest()  {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AllSongsPage = new AllSongsPage(driver);

        loginPage.login();
        homepage.chooseAllSongList;
        allsongs.contextClickFirstSong();
        allsongs.choosePlaySong();
        Assert.assertTrue(allSongs.isSongPlaying());

    }

}

