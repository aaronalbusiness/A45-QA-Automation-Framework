import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagefactory.HomePage;
import pagefactory.LoginPage;


import java.lang.reflect.Method;

public class LoginTest extends BaseTest {



    @DataProvider(name = "IncorrectLoginProviders")
    public static Object[][] getDataFromDataProviders() {
        return new Object[][]{
                {"NotexistingEmail.com", "NotExistingPassword"},
                {"demo@class.com", ""},
                {"", ""}
        };
}

    @Test (dataProvider = "IncorrectLoginProviders", description = "Different logins from dataprovider")
    public void incorrectLoginProvidersTest(Method method, String userName, String userPassword) {

        System.out.println("Running test method: " + method.getName());

        provideEmail(userName);
        providePassword(userPassword);
        clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

    @Test
    public void loginSuccessfulTest(Method method) {
        System.out.println("Running test method: " + method.getName());

        login("demo@class.com", "te$t$tudent");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar")));

        WebElement avatarImage = driver.findElement(By.cssSelector("img.avatar"));
        Assert.assertTrue(avatarImage.isDisplayed());
    }
    // the methjod below introduces POM to replace the above method which doesnt implement POM
    @Test
    public void loginSuccessfulTestTPOM() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login("demo@class.com","te$t$tudent");  //Isnt this better practice then the line below which uses 3 methods?
//        loginPage.provideEmail("demo@class.com").providePassword("te$t$tudent").clickSubmit();
       Assert.assertTrue(homePage.IsAvatarDisplayed());
    }

    @Test
    public void loginInvalidEmailValidPassswordTest(Method method) {
        System.out.println("Running test method: " + method.getName());

        login("Invalid", "te$t$tudent");
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }


    @Test
    public void loginValidEmailInvalidPassswordTest(Method method) {
        System.out.println("Running test method: " + method.getName());

        login("demo@class.com", "Invalid");
        Assert.assertEquals(driver.getCurrentUrl(), url);
    }


    @Test
    public static void loginEmptyEmailEmptyPasswordTest(Method method) {
        System.out.println("Running test method: " + method.getName());

        clickSubmit();
        Assert.assertEquals(driver.getCurrentUrl(), url);
//
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.provideEmail("").providePassword("").clickSubmit();
//        Assert.assertEquals(driver.getCurrentUrl(), url);

    }
}
