package Base;

import Pages.HomeBase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;

public class BaseTest {
    WebDriver driver;
    protected HomeBase homeBase;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void Setup()
    {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver(chromeOption());//
        driver.manage().window().maximize();
        homeBase = new HomeBase(driver);
    }

    @BeforeMethod
    public void gotoURL() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @AfterMethod
    public void takeScreenShot(ITestResult result)
    {
        if (result.getStatus() == ITestResult.SUCCESS) {
            var camera = ((TakesScreenshot) driver);
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Date today = new Date();
                String FileName = "resources/Screenshots/" + result.getName() + "-" + today.getTime() + ".png";
                //String DateTime = LocalDateTime.now().getSecond() + "" + LocalDateTime.now().getMinute()
                //        + "" + LocalDateTime.now().getHour() + "" + LocalDateTime.now().getDayOfMonth();
                Files.move(screenshot.toPath(), new File(FileName).toPath());

            } catch (Exception er) {
                er.printStackTrace();
            }
        }
    }

    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default.content_settings.popups", 0);
        //chromePrefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        //options.setHeadless(true);
        return options;
    }

}
