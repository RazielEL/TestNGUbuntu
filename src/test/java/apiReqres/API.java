package apiReqres;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API {


     static SoftAssert sa = new SoftAssert();
     static String baseURI = RestAssured.baseURI = "http://reqres.in";
     static RequestSpecification rsGet = given().header("Content-Type", "application/json");


    @Test (groups = "reqres")
    public void getListUsers() {
        Response r1 = rsGet.when().get(baseURI + "/api/users?page=2");
        List<Map<String, String>> listOfUsers = r1.jsonPath().get("data");


        System.out.println(listOfUsers);
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (listOfUsers.get(i).get("email").equals("michael.lawson@reqres.in")) {
                System.out.println(listOfUsers.get(i));
            }
        }

        Map<String, String> wholeList = r1.jsonPath().get();
        System.out.println(wholeList);
    }
    @Test (groups = "reqres")
    public void getSingleUser(){


        Response r2 = rsGet.when().get(baseURI+"/api/users/2");
        Map<String,String> singleUser = r2.jsonPath().get("data");

        sa.assertEquals(200,r2.getStatusCode());

        System.out.println(r2.getStatusCode());

        System.out.println(singleUser);

        int id = r2.jsonPath().get("data.id");
        System.out.println(id);
        sa.assertEquals(singleUser.get("id"),id);

        String email = r2.jsonPath().get("data.email");
        System.out.println(email);
        sa.assertEquals(singleUser.get("email"),email);

        String firstName = r2.jsonPath().get("data.first_name");
        System.out.println(firstName);
        sa.assertEquals(singleUser.get("first_name"),firstName);

        String lastName = r2.jsonPath().get("data.last_name");
        System.out.println(lastName);
        sa.assertEquals(singleUser.get("last_name"),lastName);

        sa.assertAll();

        }

    @Test (groups = "reqres")
    public void getSingleUserNotFound(){

        Response r3 = rsGet.get(baseURI+"/api/users/23");
        System.out.println(r3.getStatusCode());
        sa.assertEquals(404,r3.getStatusCode());
        sa.assertAll();
    }

    @Test (groups = "reqres")
    public void postCreate(){

        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        RequestSpecification rsPost1 = given().header("Content-Type","application/json").body(payload);
        Response r4 = rsPost1.when().post(baseURI + "/api/users");
        r4.prettyPrint();

        System.out.println(r4.getStatusCode());

        sa.assertEquals(r4.getStatusCode(),201);
        sa.assertAll();

    }

    @Test (groups = "reqres")
    public void putUpdate(){

        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        RequestSpecification rsPost2 = given().header("Content-Type","application/json").body(payload);
        Response r5 = rsPost2.when().post(baseURI + "/api/users/2");
        System.out.println(r5.getStatusCode());

        sa.assertEquals(r5.getStatusCode(),201);
        sa.assertAll();

    }

    @Test (groups = "reqres")
    public void postRegister(){

        String payload = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        RequestSpecification rsPost3 = given().header("Content-Type","application/json").body(payload);
        Response r6 = rsPost3.when().post(baseURI + "/api/register");
        System.out.println(r6.getStatusCode());

        sa.assertEquals(r6.getStatusCode(),201);
        sa.assertAll();


    }










    }

