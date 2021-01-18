package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeBase
{
    WebDriver driver;
    By txtUsername = By.id("txtUsername");
    By txtPassword = By.id("txtPassword");
    By btnLogin = By.id("btnLogin");

    public HomeBase(WebDriver webDriver)
    {
        driver = webDriver;
        PageFactory.initElements(driver,this);
    }

    /**
     * we need to create a page after login to return an object
     * @param un is the user name
     * @param pw is the user password
     */
    public DashboardPage Login(String un, String pw)
    {
        try
        {
            driver.findElement(txtUsername).sendKeys(un);
            driver.findElement(txtPassword).sendKeys(pw);
            driver.findElement(btnLogin).click();

        }catch (Exception err)
        {
            err.printStackTrace();
        }
        return new DashboardPage(driver);
    }
    @FindBy(name = "a")
    WebElement link;
}
