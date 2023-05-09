import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePlaylistTest extends BaseTest {
    @Test
    public void deletePlaylist() throws InterruptedException {
        // This method only works if the playlist being selected to delete is empty
        String deletedPlaylistMsg = "Deleted playlist";

        login("demo@class.com", "te$t$tudent");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar")));
        openPlaylist();
        clickDeletePlaylistBtn();
        //getDeletedPlaylistMsg();
        Thread.sleep(2000);
        Assert.assertTrue(getDeletedPlaylistMsg().contains(deletedPlaylistMsg));
    }


}
