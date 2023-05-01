import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class PlaySongTest {

    private WebDriver driver;

    @Test
    public void playSongTest() throws InterruptedException {
        login("demo@class.com", "te$t$tudent");
        playSong();
        Assert.assertTrue(isSongPlaying());
        Thread.sleep(2000); // Wait for 10 seconds
        driver.quit();

    }

    public void login(String email, String password) {
        //      Added ChromeOptions argument below to fix websocket error
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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
        WebElement playNextButton = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));

        playNextButton.click();
        playButton.click();
    }

    public boolean isSongPlaying() {
        WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();

    }
}

