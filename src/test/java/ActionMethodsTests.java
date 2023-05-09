import org.testng.annotations.Test;

public class ActionMethodsTests extends BaseTest{

    @Test
    public void actionMethodContextClick() {
        login("demo@class.com","te$t$tudent" );
        chooseAllSongList();
        contextClickFirstSong();
    }

    @Test
    public void actionMethodHover() {
        login("demo@class.com","te$t$tudent" );
        chooseAllSongList();
        hoverPlay();
    }

    @Test
    public void listOfSongsWebElements () {
        login("demo@class.com","te$t$tudent" );
        displayAllSongs();
    }
}
