import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;


import java.lang.reflect.Method;
import java.time.Duration;

public class LoginTests extends BaseTest {

    @Test
    public void actionMethods() {
        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();
        chooseAllSongList();
        contextClickFirstSong();
    }

    public void chooseAllSongList() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("a.songs[href='#!/songs']")));
        driver.findElement(By.cssSelector("a.songs[href='#!/songs']")).click();
    }

    public void contextClickFirstSong() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        WebElement firstSong = driver.findElement(By.cssSelector(".all-songs tr.song-item:nth-child(1)"));
        actions.contextClick(firstSong).perform();

    }


    @DataProvider(name = "IncorrectLoginProviders")
    public static Object[][] getDataFromDataProviders() {
        return new Object[][]{
                {"NotexistingEmail.com", "NotExistingPassword"},
                {"demo@class.com", ""},
                {"", ""}
        };
}

    @Test (dataProvider = "IncorrectLoginProviders", enabled = true, priority = 0, description = "Different logins from dataprovider")
    public void incorrectLoginProvidersTest(Method method, String userName, String userPassword) {

        System.out.println("Running test method: " + method.getName());

        provideEmail(userName);
        providePassword(userPassword);
        clickSubmit();

        String currentUrl = driver.getCurrentUrl();
        String whatIsURL = url;
        System.out.println("Current URL: " + currentUrl);
        System.out.println("url: " + whatIsURL);

        Assert.assertEquals(driver.getCurrentUrl(), url);

    }

    @Test
    public void loginSuccessfulTest(Method method) {
        System.out.println("Running test method: " + method.getName());

        provideEmail("demo@class.com");
        providePassword("te$t$tudent");
        clickSubmit();

        WebElement avatarImage = driver.findElement(By.cssSelector("img.avatar"));
        Assert.assertTrue(avatarImage.isDisplayed());
    }

    @Test
    public void loginInvalidEmailValidPassswordTest(Method method) {
        System.out.println("Running test method: " + method.getName());

        provideEmail("Invalid@class.com");
        providePassword("te$t$tudent");
        clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void loginValidEmailInvalidPassswordTest(Method method) {
        System.out.println("Running test method: " + method.getName());

        provideEmail("demo@class.com");
        providePassword("Invalid");
        clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), url);
    }


    @Test
    public static void loginEmptyEmailEmptyPasswordTest(Method method) {
        System.out.println("Running test method: " + method.getName());

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), url);

    }
}
