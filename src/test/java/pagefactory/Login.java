//package pagefactory;
//
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//
//public class Login {
//
//    //Methods
//    @FindBy(css = "[type='submit']");
//    WebElement submitButtonLocator;
//    @FindBy(css = "[type='email']");
//    WebElement emailField;
//    @FindBy(css = "[type='password']");
//    WebElement passwordField;
//
//    public void clickSubmitBtn() {
//        submitButtonLocator.click();
//    }
//
//    public void provideEmail(String email) {
//        emailField.sendKeys();
//    }
//
//    public void providePassword(String password) {
//        passwordField.sendKeys();
//    }
//
//}