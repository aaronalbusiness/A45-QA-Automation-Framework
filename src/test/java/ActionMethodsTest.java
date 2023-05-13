import org.testng.annotations.Test;

public class ActionMethodsTest extends BaseTest{

    @Test
    public void actionMethodContextClick() {
        login("demo@class.com","te$t$tudent" );
        chooseAllSongList();
        contextClickFirstSong();
    }

    @Test
    public void hoverOverPlayButton() {
        login("demo@class.com","te$t$tudent" );
        chooseAllSongList();
        hoverPlay();
//        Assert.assertTrue(hoverPlay().isDisplayed());
//        hoverPlay().click;
//        Assert.assertTrue(isSongPlaying());
    }

    @Test
    public void listOfSongsWebElements () {
        login("demo@class.com","te$t$tudent" );
        displayAllSongs();
    }
}
