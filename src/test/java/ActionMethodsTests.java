import org.testng.annotations.Test;

public class ActionMethodsTests extends BaseTest{

    @Test
    public void actionMethodContextClick() {
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        chooseAllSongList();
        contextClickFirstSong();
    }

    @Test
    public void actionMethodHover() {
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        chooseAllSongList();
        hoverPlay();
    }

    @Test
    public void listOfSongsWebElements () {
        // Requires playlist 'Playlist Dem,o" with at least 1 song
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        displayAllSongs();
    }
}
