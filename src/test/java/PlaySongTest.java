import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PlaySongTest extends BaseTest{

    private WebDriver driver;

    @Test
    public void playSongTest() throws InterruptedException {
        System.out.println("Im in playSongTest");

        login("demo@class.com", "te$t$tudent");

        System.out.println("Im in playSongTestand I logged in");

        playSong();
        Assert.assertTrue(isSongPlaying());
        Thread.sleep(2000); // Wait for 10 seconds

    }

    public void login(String email, String password) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://bbb.testpro.io/";
        driver.get(url);


        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.cssSelector("input[type='password']"));
        passwordField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();
    }

    public void playSong() {
        System.out.println("Im in playSong");

       WebElement playNextButton = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
       WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));

        //WebElement playNextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@data-testid='play-next-btn']")));
        //WebElement playButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-testid='play-btn']")));

        System.out.println("I seem to have found the 2 elements");


        playNextButton.click();
        playButton.click();

        System.out.println("and I get to the ends");
    }

    public boolean isSongPlaying() {
        WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();

    }
}

