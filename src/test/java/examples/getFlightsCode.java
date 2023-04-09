package examples;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class getFlightsCode {

    @DataProvider
    public static Object[][] currencyData() {
        return new Object[][]{
                {0, "RUH", "King Khaled International Airport", "SA"},
                {1, "BAH" ,"Bahrain International Airport", "BH"},
        };
    }

    @Test
    @UseDataProvider("currencyData")
    public void getTest(int index, String code, String airport, String CountryCode) {

        given().
        when().
                get("https://www.almosafer.com/api/flight/resource/codes/RUH,BAH").
        then().
                assertThat()
                    .statusCode(200)
                    .log().body()
                    .body("airport.code[" + index + "]", equalTo(code))
                    .body("airport.label.en[" + index + "]", equalTo(airport))
                    .body("airport.country.code[" + index + "]", equalTo(CountryCode));
    }
}

