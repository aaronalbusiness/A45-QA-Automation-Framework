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

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.provideEmail(userName);
        loginPage.providePassword(userPassword);
        loginPage.clickSubmit();

        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }


    @Test
    public void loginSuccessfulTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login("demo@class.com","te$t$tudent");
        Assert.assertTrue(homePage.IsAvatarDisplayed());
    }

    @Test
    public void loginInvalidEmailValidPassswordTest(Method method) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login("demo","te$t$tudent");
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }


    @Test
    public void loginValidEmailInvalidPassswordTest(Method method) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login("demo@class.com","te$t");
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }

    @Test
    public static void loginEmptyEmailEmptyPasswordTest(Method method) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login("","");
        Assert.assertEquals(getDriver().getCurrentUrl(), url);
    }
}
