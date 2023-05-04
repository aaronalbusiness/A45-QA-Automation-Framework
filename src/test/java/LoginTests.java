import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;



import java.lang.reflect.Method;
import java.time.Duration;

public class LoginTests extends BaseTest {

    @DataProvider(name = "IncorrectLoginProviders")
    public static Object[][] getDataFromDataProviders() {
        return new Object[][]{
                {"NotexistingEmail.com", "NotExistingPassword"},
                {"demo@class.com", ""},
                {"", ""} };
    }

    @Test (dataProvider = "IncorrectLoginProviders", enabled = true, priority = 0, description = "Different logins from dataprovider")
    public void incorrectLoginProvidersTest(Method method, String userName, String userPassword) {

        provideEmail(userName);
        providePassword(userPassword);
        clickSubmit();

        System.out.println("Running test method: " + method.getName());
        String currentUrl = driver.getCurrentUrl();
        String whatIsURL = url;
        System.out.println("Current URL: " + currentUrl);
        System.out.println("url: " + whatIsURL);

        Assert.assertEquals(driver.getCurrentUrl(), url);

    }

    @Test
    public void loginSuccessfulTest(Method method) {

        login("demo@class.com", "te$t$tudent");
        Assert.assertEquals(driver.getCurrentUrl(), url);

        System.out.println("Running test method: " + method.getName());
        String currentUrl = driver.getCurrentUrl();
        String whatIsURL = url;
        System.out.println("Current URL: " + currentUrl);
        System.out.println("url: " + whatIsURL);
    }

    @Test
    public void loginInvalidEmailValidPassswordTest(Method method) {

        login("Invalid@class.com","te$t$tudent");

        System.out.println("Running test method: " + method.getName());
        String currentUrl = driver.getCurrentUrl();
        String whatIsURL = url;
        System.out.println("Current URL: " + currentUrl);
        System.out.println("url: " + whatIsURL);
   }

    @Test
    public void loginValidEmailInvalidPassswordTest(Method method) {

        login("demo@class.com", "Invalid");

        System.out.println("Running test method: " + method.getName());
        String currentUrl = driver.getCurrentUrl();
        String whatIsURL = url;
        System.out.println("Current URL: " + currentUrl);
        System.out.println("url: " + whatIsURL);
    }

    @Test
    public void loginEmptyEmailEmptyPasswordTest(Method method) {

        login("", "");

        System.out.println("Running test method: " + method.getName());
        String currentUrl = driver.getCurrentUrl();
        String whatIsURL = url;
        System.out.println("Current URL: " + currentUrl);
        System.out.println("url: " + whatIsURL);
    }
}
