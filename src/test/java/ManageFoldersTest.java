import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FoldersPage;
import pages.HomePage;
import pages.LoginPage;


public class ManageFoldersTest {
     WebDriver driver = new ChromeDriver();
     private LoginPage loginPage;
     private HomePage homePage;
     private FoldersPage foldersPage;

     @Parameters({"URL","validUsername", "validPassword"})
        @BeforeMethod
        public void setup (String url, String username, String password){
            System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
            loginPage = new LoginPage(driver);
            homePage= new HomePage(driver);
            foldersPage= new FoldersPage(driver);
            driver.get(url);
            loginPage.doLogin(username,password);
     }

    @Test(description = "Create a Folder",priority = 1)
    public void createFolder() {
       homePage.goToManageOrders();
       foldersPage.createNewFolder("Test Folder");
        foldersPage.verifyIftheFolderWasCreated();
   }

    @Test(description = "Create a Folder without name",priority = 1)
    public void createFolderWithoutName() {
        homePage.goToManageOrders();
        foldersPage.createNewFolder("");
        foldersPage.verifyIftheFolderWasCreated();
        Assert.assertFalse(foldersPage.alert.getText().contains("A new folder is created"), "Failed. The folder was created");
    }

    @AfterMethod
    public void clousureSteps() {
        homePage.signOut();
        homePage.homeOption.click();
    }

    @AfterTest
    public void teardown() {
         driver.quit();
    }
}
