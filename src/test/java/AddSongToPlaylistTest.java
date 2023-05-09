
import org.testng.Assert;
import org.testng.annotations.Test;



public class AddSongToPlaylistTest extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {

        String newSongAddedNotificationText = "Added 1 song into";

       //Methods from Basetest
        login("demo@class.com", "te$t$tudent");
        Thread.sleep(2000);
        searchSong("Stance");
        clickViewAllBtn();
        selectFirstSongResult();
        clickAddToBtn();
        choosePlaylist();

        Assert.assertTrue(getNotificationText().contains(newSongAddedNotificationText));
        //driver.quit();
    }

}