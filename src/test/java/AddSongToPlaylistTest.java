
import org.testng.Assert;
import org.testng.annotations.Test;



public class AddSongToPlaylistTest extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {

        String newSongAddedNotificationText = "Added 1 song into";

        navigateToPage();
        provideEmail("aaronalbusiness@gmail.com");
        providePassword("te$t$tudent");
        clickSubmit();
        searchSong("Reactor");
        clickViewAllBtn();
        selectFirstSongResult();
        clickAddToBtn();
        choosePlaylist();
        Assert.assertTrue(getNotificationText().contains(newSongAddedNotificationText));

    }

}