package com.petclinic;

import com.petclinic.pageobject.NewOwnerPage;
import com.petclinic.pageobject.OwnersPage;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.List;

@Feature("Owner")

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
    @Step("Open 'Add new owner' page")
    public void beforeClass() {
        owner = new Owner();
        ownersPage = new OwnersPage(driver);
        ownersPage.openPage();
        newOwnerPage = ownersPage.clickAddOwnerBtn();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add new Owner")
    public void addNewOwnerTest() {
        owner.setFirstName("Simple");
        owner.setLastName("User");
        owner.setAddress("Main Street");
        owner.setCity("Dnipro");
        owner.setTelephone("05075869");


        newOwnerPage.fillOwner(owner);
        ownersPage = newOwnerPage.clickAddOwnerButton();

        goToOwnersPage();
        ownersPage.verifyOwnersListContainsNewlyAdded(owner);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'First name' should be {isMoreThan}")
    public void verifyFirstNameLength() {
        goToNewOwnerPage();
        newOwnerPage.setFirstName("1");
        assertThat(newOwnerPage.errorMessage(firstName + isMoreThan).isDisplayed()).isTrue();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'Last name' should be {isMoreThan}")
    public void verifyLastNameLength() {
        newOwnerPage.setLastName("1");
        assertThat(newOwnerPage.errorMessage(lastName + isMoreThan).isDisplayed()).isTrue();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'Phone' should be {isDigitsOnly}")
    public void verifyPhone() {
        newOwnerPage.setTelephone("1wqewq");
        assertThat(newOwnerPage.errorMessage(telephone + isDigitsOnly).isDisplayed()).isTrue();
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'First name' is required field")
    public void verifyFirstNameRequired() {
        newOwnerPage.clearField("firstName");
        assertThat(newOwnerPage.errorMessage(firstName + isRequired).isDisplayed()).isTrue();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'Last name' is required field")
    public void verifyLastNameRequired() {
        newOwnerPage.clearField("Last name");
        assertThat(newOwnerPage.errorMessage(lastName + isRequired).isDisplayed()).isTrue();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'Phone' is required field")
    public void verifyPhoneRequired() {
        newOwnerPage.clearField("Phone");
        assertThat(newOwnerPage.errorMessage(telephone + isRequired).isDisplayed()).isTrue();
    }


}
