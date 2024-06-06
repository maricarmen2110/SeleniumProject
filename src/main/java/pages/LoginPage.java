package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id ="username")
    public WebElement txtUsername;

    @FindBy(id ="password")
    public WebElement txtPassword;

    @FindBy(xpath ="//button[@type='submit' and @data-cy='submit' and contains(text(), 'Sign in')]")
    public WebElement btnSignIn;

    @FindBy(xpath ="//div[@class='alert alert-danger fade show' and @data-cy='loginError']")
    public WebElement failureMessage;

    public void doLogin(String username, String password){
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnSignIn.click();
    }
}
