import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePlaylistTest extends BaseTest {
    @Test
    public void deletePlaylist() throws InterruptedException {
        // This method only works if the playlist being selected to delete is empty
        String deletedPlaylistMsg = "Deleted playlist";

        login("demo@class.com", "te$t$tudent");
        Thread.sleep(2000);
        openPlaylist();
        clickDeletePlaylistBtn();
        Thread.sleep(500);
        Assert.assertTrue(getDeletedPlaylistMsg().contains(deletedPlaylistMsg));
    }


}
