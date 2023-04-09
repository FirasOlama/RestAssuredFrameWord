package examples;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class getCurrencyList {

    @DataProvider
    public static Object[][] currencyData() {
        return new Object[][]{
                {0, "Saudi Riyal", "SAR"},
                {1, "United Arab Emirates Dirham", "AED"},
                {2, "Qatari Rial", "QAR"}
        };
    }

    @Test
    @UseDataProvider("currencyData")
    public void getTest(int i, String name, String code) {

        given().
        when().
                get("https://www.almosafer.com/api/system/currency/list").
        then().
                assertThat()
                    .statusCode(200)
                    .log().body()
                    .body("equivalent.name[" + i + "]", equalTo(name))
                    .body("equivalent.code[" + i + "]", equalTo(code));

    }
}

