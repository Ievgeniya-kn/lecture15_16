package com.petclinic.API;

import com.petclinic.model.Speciality;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SpecialityAPI extends BaseAPI{
    public Speciality specialityAPI =new Speciality();
    public Speciality specialityPost = new Speciality();
    public String additionalURL="/specialties";

    @BeforeClass
    public void setUp() {  postNewSpeciality();  }

    @Test
    public void postNewSpeciality() {

        specialityAPI.setName("Shark");

        specialityPost = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(specialityAPI)
                .post(additionalURL)
                .then()
                .statusCode(201)
                .extract().body()
                .as(Speciality.class);

        assertTrue(specialityPost.equals(specialityPost));
//        System.out.println(specialityPost);
//        System.out.println(specialityPost.getId());


    }

    @Test
    public void putSpecialityById() {
        String extendedAPIUrl = additionalURL+"/"+specialityPost.getId();
        System.out.println(extendedAPIUrl);
        specialityPost.setName("Whale");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(specialityAPI)
                .put(extendedAPIUrl)
                .then()
                .statusCode(204);

        getSpecialityById();
    }



    @Test
    public void getSpecialityById() {
        String extendedAPIUrl = additionalURL+"/"+specialityPost.getId();
        System.out.println(extendedAPIUrl);
        specialityAPI = RestAssured.given()
                .contentType(ContentType.JSON)
                .put(extendedAPIUrl)
                .then()
                .statusCode(200)
                .extract().body()
                .as(Speciality.class);

        assertTrue(specialityAPI.equals(specialityPost));
    }

}
