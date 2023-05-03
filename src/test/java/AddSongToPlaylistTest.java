
import org.testng.Assert;
import org.testng.annotations.Test;



public class AddSongToPlaylistTest extends BaseTest {

    @Test
    public void addSongToPlaylist() throws InterruptedException {

        String newSongAddedNotificationText = "Added 1 song into";

       //Methods from Basetest
        navigateToPage();
        provideEmail("aaronalbusiness@gmail.com");
        providePassword("te$t$tudent");
        clickSubmit();


        //Methods from Basetest
        searchSong("Funeral Music");
        clickViewAllBtn();
        selectFirstSongResult();
        clickAddToBtn();
        choosePlaylist();

        Assert.assertTrue(getNotificationText().contains(newSongAddedNotificationText));
        //driver.quit();
    }

}