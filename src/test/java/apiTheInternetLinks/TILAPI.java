package apiTheInternetLinks;

import utils.ExcelReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TILAPI {


    @Test (groups = "API")
    public void APITestTIL(){

        SoftAssert sa = new SoftAssert();
        String baseURI = RestAssured.baseURI = "http://localhost:7080";
        RequestSpecification rs = given().header("Content-Type", "application/json");

        ExcelReader.openExcel("/home/razz/IdeaProjects/TestNGUbuntu/resources/ListOfLinks.xlsx");
        ExcelReader.getSheet("Sheet1");
        int rowCount = ExcelReader.getRowCount();


        int collumnCount = ExcelReader.getColumntCount(1);


        List<String> endpoints = new ArrayList<>();

        for (int row = 1; row < rowCount; row++){
            endpoints.add(ExcelReader.getCellData(row,2));
        }

        List<String> failedStatusCode = new ArrayList<>();

        for (int i = 0; i<endpoints.size(); i++){
            Response response = rs.when().get(baseURI + endpoints.get(i));

            if(response.getStatusCode() == 200){
                System.out.println("Status code of GET response: " + response.getStatusCode() + " of the endpoint of : " + endpoints.get(i));
            }else{
                System.out.println("Status code of GET response: " + response.getStatusCode() + " of the endpoint of : " + endpoints.get(i));
                failedStatusCode.add(endpoints.get(i));
            }
            sa.assertEquals(response.getStatusCode(),200);
        }

        System.out.println();
        System.out.println("Failed API Tests: ");
        for (int i = 0; i<failedStatusCode.size(); i++){
            System.out.println(failedStatusCode.get(i));
        }

        sa.assertAll();









    }



}
