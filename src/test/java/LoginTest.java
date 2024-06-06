import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import java.util.concurrent.TimeUnit;

public class LoginTest {
     WebDriver driver = new ChromeDriver();
     private LoginPage loginPage;
     private HomePage homePage;

     @Parameters({"URL"})
        @BeforeMethod
        public void setup (String url){
            System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
            loginPage = new LoginPage(driver);
            homePage= new HomePage(driver);
            driver.get(url);
        }

   @Parameters({"validUsername", "validPassword"})
    @Test(description = "Log in with valid credentials",priority = 1)
    public void signUpWithValidCredentials (String username, String password) {
       loginPage.doLogin(username,password);
       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
       Assert.assertEquals(homePage.welcomeMessage.getText(),"Welcome to Ensolvers QA Challenge!", "Expected Result");
       homePage.signOut();
    }

    @Parameters({"invalidUsername", "invalidPassword"})
    @Test(description = "Log in with invalid credentials",priority = 2)
    public void signUpWithInvalidCredentials (String username, String password) {
        loginPage.doLogin(username,password);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertEquals(loginPage.failureMessage.getText(),"Failed to sign in! Please check your credentials and try again.", "Expected Result");
    }

    @Parameters({"validUsername", "invalidPassword"})
    @Test(description = "Log in with valid username and invalid password",priority = 3)
    public void signUpWithValidUsernameAndInvalidPassword(String username, String password) {
        loginPage.doLogin(username,password);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertEquals(loginPage.failureMessage.getText(),"Failed to sign in! Please check your credentials and try again.", "Expected Result");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
