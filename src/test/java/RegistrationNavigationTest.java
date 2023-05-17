import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegistrationNavigationTest extends BaseTest {
    @Test
    public void registrationNavigation() {
        WebElement registrationLink = getDriver().findElement(By.cssSelector("[id='hel']"));
        registrationLink.click();

        String registrationUrl = "https://bbb.testpro.io/registration.php";
        Assert.assertEquals(getDriver().getCurrentUrl(), registrationUrl);
    }
}

