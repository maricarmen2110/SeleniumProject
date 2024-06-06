package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//h2[text()=\"Welcome to Ensolvers QA Challenge!\"]")
    public WebElement welcomeMessage;

    @FindBy(xpath ="//span[text()='Account']")
    public WebElement ddAccount;

    @FindBy(xpath ="//a[@class='dropdown-item' and @data-cy='logout']")
    public WebElement logoutOption;

    @FindBy(xpath ="//span[text()='Home']")
    public WebElement homeOption;

    @FindBy(xpath ="//button[contains(@class, 'btn-info') and contains(., 'Manage To-Do Items')]")
    public WebElement btnManageToDoItems;

    @FindBy(xpath ="//button[contains(text(), 'Manage Folders')]")
    public WebElement btnManageFolders;

    public void signOut(){
    ddAccount.click();
    logoutOption.click();
    }

    public void goToManageToDoItems (){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        btnManageToDoItems.click();
    }

    public void goToManageOrders (){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        btnManageFolders.click();
    }


}

