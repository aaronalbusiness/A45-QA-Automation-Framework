import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePlaylistTest extends BaseTest {
    @Test
    public void deletePlaylist() throws InterruptedException {
        String deletedPlaylistMsg = "Deleted playlist";

        login("demo@class.com", "te$t$tudent");
        openPlaylist();
        clickDeletePlaylistBtn();
        Assert.assertTrue(getDeletedPlaylistMsg().contains(deletedPlaylistMsg));
    }
    public void openPlaylist() {
       // WebElement emptyPlaylist = driver.findElement(By.cssSelector(".playlist:nth-child(3)"));
        WebElement emptyPlaylist = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".playlist:nth-child(3)")));
        emptyPlaylist.click();
    }

    public void clickDeletePlaylistBtn() throws InterruptedException {
        //WebElement deletePlaylist = driver.findElement(By.cssSelector(".btn-delete-playlist"));
        WebElement deletePlaylist  = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-delete-playlist")));
        deletePlaylist.click();
    }

    public String getDeletedPlaylistMsg() {
        //WebElement notificationsMsg = driver.findElement( By.cssSelector("div.success.show"));
        WebElement notificationsMsg  = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.success.show")));
        return notificationsMsg.getText();
    }

}
