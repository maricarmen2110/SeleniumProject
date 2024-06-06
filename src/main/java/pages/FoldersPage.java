package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FoldersPage {

    WebDriver driver;

    public FoldersPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id ="jh-create-entity")
    public WebElement btnCreateNewFolder;

    @FindBy(id ="folder-name")
    public WebElement txtName;

    @FindBy(id ="save-entity")
    public WebElement btnSave;

    @FindBy(className ="Toastify__toast-body")
    public WebElement alert;

    public void createNewFolder(String folderName){
    btnCreateNewFolder.click();
    txtName.sendKeys(folderName);
    btnSave.click();
    }

    public void verifyIftheFolderWasCreated(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String textAlert = alert.getText();
        if (textAlert.contains("A new folder is created")) {
            System.out.println("Folder Created");
        } else {
            System.out.println("Error: Failure in the creation");
        }
    }
}

