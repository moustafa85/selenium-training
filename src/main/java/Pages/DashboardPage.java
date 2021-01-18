package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {
    WebDriver driver;
    By lnkAdmin = By.id("menu_admin_viewAdminModule");
    By lnkRecruitter = By.id("menu_recruitment_viewRecruitmentModule");
    By usericon = By.id("welcome");
    By pnlWelcome = By.id("welcome-menu");
    By lnkLogout = By.xpath("//a[@href='/index.php/auth/logout']");

    public DashboardPage(WebDriver webDriver) {
        driver = webDriver;
    }

    public boolean CheckElements() throws InterruptedException {
        Thread.sleep(2000);
        if (driver.findElement(lnkAdmin).isEnabled())
            return true;
        else
            return false;
    }

    public void logout() {
        driver.findElement(usericon).click();
        System.out.println("user Icon \t" + driver.findElement(usericon).isDisplayed());
        WebElement webElement = driver.findElement(pnlWelcome);
        System.out.println("Welcome Panel" + webElement.findElement(lnkLogout).isDisplayed() + "\t vs. driver" + driver.findElement(lnkLogout).isDisplayed());
        if (driver.findElement(lnkLogout).isDisplayed()) {
            driver.findElement(lnkLogout).click();
        }
    }
}
