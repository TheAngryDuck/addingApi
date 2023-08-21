package com.Add.Api;

import com.Add.Api.entity.RecordDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = {"spring.main.allow-bean-definition-overriding=true", "server.servlet.context-path=/"})
public class AdditionControllerTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void givenTwoNumbersTheCorrectResultIsReturned() {
        Map<String, Integer> params = new HashMap<>();
        params.put("number1",1);
        params.put("number2",1);
        given().params(params).get("/add").then().body("result", equalTo(2));
    }

    @Test
    public void givenOneNumberTheCorrectErrorIsReturned() {
        Map<String, Integer> params = new HashMap<>();
        params.put("number1",1);
        given().params(params).get("/add").then().statusCode(400);
    }

    @Test
    public void givenTheOrderingParameterEndpointReturnsArrayInCorrectOrder() {
        when().delete("/records").then().statusCode(200);

        Map<String, Integer> params1 = new HashMap<>();
        params1.put("number1",1);
        params1.put("number2",2);
        given().params(params1).get("/add");

        Map<String, Integer> params2 = new HashMap<>();
        params2.put("number1",3);
        params2.put("number2",4);
        given().params(params2).get("/add");

        Map<String, Boolean> params3 = new HashMap<>();
        params3.put("ascend",true);
        RecordDto[] responseBody = given().params(params3).get("/records").then().statusCode(200).extract()
                .as(RecordDto[].class);

        assertThat(responseBody.length).isEqualTo(2);
        assertThat(responseBody[0].getResult()).isEqualTo(3);
        assertThat(responseBody[1].getResult()).isEqualTo(7);
    }

    @Test
    public void givenNumberAndOrderEndpointReturnsCorrectObjectsInCorrectOrder() {
        when().delete("/records").then().statusCode(200);

        Map<String, Integer> params1 = new HashMap<>();
        params1.put("number1",1);
        params1.put("number2",2);
        given().params(params1).get("/add");

        Map<String, Integer> params2 = new HashMap<>();
        params2.put("number1",3);
        params2.put("number2",4);
        given().params(params2).get("/add");

        Map<String, Integer> params3 = new HashMap<>();
        params3.put("number1",4);
        params3.put("number2",4);
        given().params(params3).get("/add");

        Map<String, String> params4 = new HashMap<>();
        params4.put("ascend","true");
        params4.put("number","3");
        RecordDto[] responseBody = given().params(params4).get("/records").then().statusCode(200).extract()
                .as(RecordDto[].class);

        assertThat(responseBody.length).isEqualTo(2);
        assertThat(responseBody[0].getResult()).isEqualTo(3);
        assertThat(responseBody[1].getResult()).isEqualTo(7);
    }

}
