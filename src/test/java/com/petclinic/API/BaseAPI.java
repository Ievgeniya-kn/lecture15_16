package com.petclinic.API;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseAPI {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://139.59.149.247";
        RestAssured.port = 9966;
        RestAssured.basePath = "/petclinic/api";
    }
}
