import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ToDoItemsPage;

public class ManageToDoItemsTest {
     WebDriver driver = new ChromeDriver();
     private LoginPage loginPage;
     private HomePage homePage;
     private ToDoItemsPage toDoItemsPage;

     @Parameters({"URL","validUsername", "validPassword"})
        @BeforeMethod
        public void setup (String url, String username, String password){
            System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
            loginPage = new LoginPage(driver);
            homePage= new HomePage(driver);
            toDoItemsPage = new ToDoItemsPage(driver);
            driver.get(url);
            loginPage.doLogin(username,password);
     }

    @Test(description = "Create a To Do Item")
    public void createToDoItem () {
       homePage.goToManageToDoItems();
       toDoItemsPage.createNewToDoItem("Test","Test");
      toDoItemsPage.verifyIftheActionWasDone("A new toDoItem is created");
   }

   @Test(description = "Create a To Do Item with Empty values",priority = 2)
    public void createToDoItemWithEmptyValues () {
        homePage.goToManageToDoItems();
        toDoItemsPage.createNewToDoItemEmpty();
        Assert.assertTrue(toDoItemsPage.alert.getText().contains("A new toDoItem is created"), "Failed. The item was created");
    }

    @Test(description = "Edit To Do item")
    public void editToDoItem() {
        homePage.goToManageToDoItems();
        toDoItemsPage.editToDoItem();
        toDoItemsPage.verifyIftheActionWasDone("A toDoItem is updated");
        Assert.assertTrue(toDoItemsPage.alert.getText().contains("A toDoItem is updated"), "Failed. We got a different message");
    }
    @Test(description = "Delete To Do item")
    public void deleteToDoItem() {
        homePage.goToManageToDoItems();
        toDoItemsPage.deleteToDoItem();
        toDoItemsPage.verifyIftheActionWasDone("A toDoItem is deleted");
       Assert.assertTrue(toDoItemsPage.alert.getText().contains("A toDoItem is deleted"), "Failed. We got a different message");
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
