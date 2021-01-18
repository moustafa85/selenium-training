package HomePage;

import Base.BaseTest;
import Pages.DashboardPage;
import Utilities.ExcellReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class HomeTest extends BaseTest
{

    @Test(groups = {"Check", "All"})
    public void checkFunctionality() throws InterruptedException
    {
        System.out.println("Check Parallel execution");
    }

    @DataProvider(name = "returntestdata")
    public  Object[][] readTestData() throws IOException {
        ExcellReader reader = new ExcellReader();
        return reader.getData("TestingScenarios.xlsx");

    }
    @Test(groups = {"Check"}, dataProvider = "returntestdata")
    public void AccessTestCases(String tcID, String ProcessType, String CourtType,String Category, String RequestTypeID, String DocumentTypeID, String Requester, String ApplicantType, String Respondent, String DataType, String expectedResult, String actualResult) throws InterruptedException, IOException
    {

        DashboardPage dashboardPage = homeBase.Login("Admin", "admin123");
        if (dashboardPage.CheckElements())
            System.out.println("Passed in first Check" + dashboardPage.CheckElements());
        else
            System.out.println("Failed in first Check");
        dashboardPage.logout();
    }

    @Test(groups = {"Check"}, dataProvider = "returntestdata")
    public void AccessTestCase(String tcID, String ProcessType, String CourtType,String Category, String RequestTypeID, String DocumentTypeID, String Requester, String ApplicantType, String Respondent, String DataType, String expectedResult, String actualResult) throws InterruptedException, IOException
    {

        DashboardPage dashboardPage = homeBase.Login("Admin", "admin123");
        if (dashboardPage.CheckElements())
            System.out.println("Passed in first Check" + dashboardPage.CheckElements());
        else
            System.out.println("Failed in first Check");
        dashboardPage.logout();
    }

}
