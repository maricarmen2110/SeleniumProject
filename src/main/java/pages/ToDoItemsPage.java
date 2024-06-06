package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ToDoItemsPage {

    WebDriver driver;

    public ToDoItemsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id ="jh-create-entity")
    public WebElement btnCreateNewToDoItem;

    @FindBy(xpath ="//*[@id=\"to-do-item-title\"]")
    public WebElement txtTitle;

    @FindBy(id ="to-do-item-description")
    public WebElement txtDescription;

    @FindBy(id ="to-do-item-folder")
    public WebElement DDFolder;

    @FindBy(id ="save-entity")
    public WebElement btnSave;

    @FindBy(className ="Toastify__toast-body")
    public WebElement alert;

    @FindBy(xpath ="//span[@class='d-none d-md-inline']")
    public WebElement btnView;

    @FindBy(xpath="//span[contains(text(), 'Edit')]")
    public WebElement btnEditToDoItem;

    @FindBy(xpath="//a[@data-cy='entityDeleteButton']")
    public WebElement btnDelete;

    @FindBy(xpath="//button[@data-cy='entityConfirmDeleteButton']")
    public WebElement btnConfirmDelte;

    public void createNewToDoItem(String title, String description){
        btnCreateNewToDoItem.click();
        txtTitle.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         txtTitle = wait.until(ExpectedConditions.elementToBeClickable(By.id("to-do-item-title")));
        txtTitle.clear();
        txtTitle.sendKeys(title);
        txtDescription.sendKeys(description);
        verifyIfTheDropdownHaveOptions();
        }

    public void createNewToDoItemEmpty(){
        btnCreateNewToDoItem.click();
        txtTitle.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement txtTitle = wait.until(ExpectedConditions.elementToBeClickable(By.id("to-do-item-title")));
        txtTitle.clear();
        txtTitle.sendKeys("");
        txtDescription.sendKeys("");
        btnSave.click();
    }

    public void verifyIfTheDropdownHaveOptions(){
        Select dropdown = new Select(DDFolder);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        java.util.List<WebElement> options = dropdown.getOptions();
        if(options.size() > 1) {
            dropdown.selectByIndex(1);
            btnSave.click();
        } else {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            btnSave.click();
        }

    }
    public void verifyIftheActionWasDone(String expectedText){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String textAlert = alert.getText();
        if (textAlert.contains(expectedText)) {
            System.out.println("Sucesfully completed");
        } else {
            System.out.println("Error: Failure in action expected or different message");
        }
    }

    public void editToDoItem(){
        btnView.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        btnEditToDoItem.click();
        txtTitle.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement txtTitle = wait.until(ExpectedConditions.elementToBeClickable(By.id("to-do-item-title")));
        txtTitle.clear();
        txtTitle.sendKeys("Item Updated");
        txtDescription.sendKeys("Description updated");
        btnSave.click();
    }

    public void deleteToDoItem(){
        btnDelete.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.elementToBeClickable(btnConfirmDelte));
        btnConfirmDelte.click();

    }
}

