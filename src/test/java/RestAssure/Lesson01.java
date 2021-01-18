package RestAssure;

import Utilities.ExcellReader;
import groovy.json.JsonParser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.util.Asserts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import org.json.simple.JSONObject;

public class Lesson01 {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Hello Allure World")
    @Feature("Feature 1")
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Hello Allure World 2")
    @Feature("Feature 2")
    public void priningBody() {

        given().log().all().
                when().
                get("http://zippopotam.us/CA/A0A").
                then().log().body();
    }

    @Test(dataProvider = "readInputFile")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Hello Allure World 3")
    @Feature("Feature 3")
    public void hamcrestMatches(String countryCode, String zipCode, String city) {
        int i;
        String zipcodee;
        try {
            i = (int) (Double.parseDouble(zipCode));
            zipcodee = "" + i;

        } catch (Exception err) {
            zipcodee = zipCode;
        }

        System.out.println("" + countryCode + "\t" + city + "\t" + zipcodee);

        given().
                when().pathParam("cC", countryCode).pathParam("zC", zipcodee).
                get("http://zippopotam.us/{cC}/{zC}").
                then().
                assertThat().
                body("places.'place name'", hasItem(city));
        //body("places.'place name'", hasItem("Beverly Hills"));
        System.out.println("" + countryCode + "\t" + city + "\t" + zipCode);
    }

    @DataProvider(name = "readInputFile")
    public Object[][] readData() throws IOException {
        ExcellReader reader = new ExcellReader();
        return reader.getData("RestAssured.xlsx");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Hello Allure World 4")
    @Feature("Feature 4")
    public void checkPassingParamsinRequest() {
        var sepc = new RequestSpecBuilder().setBaseUri("http://zippopotam.us").build();
        given().spec(sepc).when().get("CA/A0A").then().statusCode(200);

    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("Hello Allure World 5")
    @Feature("Feature 5")
    public void checkPassingParamsinResponse() {
        var requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://zippopotam.us").build();
        var sepc = new ResponseSpecBuilder().expectContentType(ContentType.JSON).
                expectStatusCode(200).build();
        given().spec(requestSpecification).when().get("CA/A0A").then().spec(sepc);

    }
    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Hello Allure World 6")
    @Feature("Feature 6")
    public void retrieveResponseParams() {
        var requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://zippopotam.us").build();
        var sepc = new ResponseSpecBuilder().expectContentType(ContentType.JSON).
                expectStatusCode(200).build();

        String returnval = given().spec(requestSpecification).when().get("CA/A0A").then().spec(sepc).extract().path("'country abbreviation'");
        List<String> returnvalue = given().spec(requestSpecification).when().get("CA/A0A").then().spec(sepc).extract().path("places.'place name'");
        for(String val : returnvalue)
            System.out.println("Hello APIs \n"+val);

        System.out.println("Hello APIs \n"+returnval);

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Hello Allure World 7")
    @Feature("Feature 8")
    public void restAssuredResponse()
    {
        Response response = RestAssured.get("https://parabank.parasoft.com/parabank/services/bank/customers/12212");
        System.out.println( response.getStatusCode());
        System.out.println(""+response.getBody().path("customer.address.street"));

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Hello Allure World 9")
    @Feature("Feature 9")
    public void restAssuredSyntax()
    {
        given().get("https://parabank.parasoft.com/parabank/services/bank/customers/12212").
        then().
                assertThat().
                body("customer.address.street",equalTo("1431 Main St"))
                .and().assertThat().
                body("customer.address.state",equalTo("CA"));

        String retval = get("https://parabank.parasoft.com/parabank/services/bank/customers/12212").andReturn().asString();
        XmlPath xmlvar = new XmlPath(retval).setRoot("customer");
        System.out.println( "Customer ID: \t"+xmlvar.getString("id"));
        Assert.assertEquals(xmlvar.getString("id"),"12212");
        JsonParser jsonParser;
        JSONObject jsonObject = new JSONObject();
    }

}
