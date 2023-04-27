
import org.testng.Assert;
import org.testng.annotations.Test;



public class AddSongToPlaylist extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {

        String newSongAddedNotificationText = "Added 1 song into";

        openLoginUrl();
        enterEmail("aaronalbusiness@gmail.com");
        enterPassword("te$t$tudent");
        clickSubmit();
        searchSong("Reactor");
        clickViewAllBtn();
        selectFirstSongResult();
        clickAddToBtn();
        choosePlaylist();
        Assert.assertTrue(getNotificationText().contains(newSongAddedNotificationText));

    }

}