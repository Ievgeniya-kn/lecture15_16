package com.petclinic;

import com.petclinic.pageobject.NewOwnerPage;
import com.petclinic.pageobject.OwnersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.List;

public class NewOwnersTest extends TestBase {
    OwnersPage ownersPage;
    Owner owner;
    NewOwnerPage newOwnerPage;
    String firstName = "First name";
    String lastName = "Last name";
    String telephone = "Phone number";
    String isRequired = " is required";
    String isMoreThan = " must be at least 2 characters long";
    String isDigitsOnly = " only accept digits";


    @BeforeClass
    public void beforeClass() {
        owner = new Owner();
        ownersPage = new OwnersPage(driver);
        ownersPage.openPage();
        newOwnerPage = ownersPage.clickAddOwnerBtn();
    }


    @Test
    public void addNewOwnerTest() {
        owner.setFirstName("Simple");
        owner.setLastName("User");
        owner.setAddress("Main Street");
        owner.setCity("Dnipro");
        owner.setTelephone("05075869");


        newOwnerPage.fillOwner(owner);
        ownersPage = newOwnerPage.clickAddOwnerButton();

        goToOwnersPage();
        List<Owner> ownersNames = ownersPage.getOwnersList();
        assertThat(ownersNames).contains(owner);
    }

    @Test
    public void VerifyFirstNameLength() {
        newOwnerPage.setFirstName("1");
        assertThat(newOwnerPage.errorMessage(firstName + isMoreThan).isDisplayed()).isTrue();
    }

    @Test
    public void VerifyLastNameLength() {
        newOwnerPage.setLastName("1");
        assertThat(newOwnerPage.errorMessage(lastName + isMoreThan).isDisplayed()).isTrue();
    }

    @Test
    public void VerifyPhone() {
        newOwnerPage.setTelephone("1wqewq");
        assertThat(newOwnerPage.errorMessage(telephone + isDigitsOnly).isDisplayed()).isTrue();
    }


    @Test
    public void VerifyFirstNameRequired() {
        newOwnerPage.clearField("firstName");
        assertThat(newOwnerPage.errorMessage(firstName + isRequired).isDisplayed()).isTrue();
    }

    @Test
    public void VerifyLastNameRequired() {
        newOwnerPage.clearField("Last name");
        assertThat(newOwnerPage.errorMessage(lastName + isRequired).isDisplayed()).isTrue();
    }

    @Test
    public void VerifyPhoneRequired() {
        newOwnerPage.clearField("Phone");
        assertThat(newOwnerPage.errorMessage(telephone + isRequired).isDisplayed()).isTrue();
    }


}
