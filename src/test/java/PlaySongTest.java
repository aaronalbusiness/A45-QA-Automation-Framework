import org.testng.Assert;
import org.testng.annotations.Test;



public class PlaySongTest extends BaseTest{

    //private WebDriver driver;

    @Test
    public void playSongTest() throws InterruptedException {
        login("demo@class.com", "te$t$tudent");
        chooseAllSongList();
        contextClickFirstSong();
        playSong();
        Assert.assertTrue(isSongPlaying());
    }

}

