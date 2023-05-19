package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllSongsPage extends BasePage {
    public AllSongsPage(WebDriver givenDriver) {
        super(givenDriver);
    }

    //store web elements using By keyword
    By firstSongElement = By.cssSelector(".all-songs tr.song-item:nth-child(1)");
    private final By playOption = By.xpath("//span[contains(text(),'Play')]");//feel free to change the selector
    public void contextClickFirstSong() {
        contextClick(firstSongElement);
    }

//    public void playSong() {
//        WebElement playNextButton = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
//        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
//
//        playNextButton.click();
//        playButton.click();
//    }

    public void clickPlayOption() {
        click(playOption);
    }
}
