package com.petclinic;

import com.petclinic.pageobject.NewOwnerPage;
import com.petclinic.pageobject.NewVeterinarianPage;
import com.petclinic.pageobject.OwnersPage;
import com.petclinic.pageobject.VeterinariansPage;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Veterinarian")
public class NewVeterinarianTest extends TestBase {
    VeterinariansPage veterinariansPage;
    Veterinarian veterinarian;
    NewVeterinarianPage newVeterinarianPage;
    String firstName = "First name";
    String lastName = "Last name";
    String type = "Type";
    String isRequired = " is required";
    String isMoreThan = " must be at least 2 characters long";


    @BeforeClass
    @Step("Open Veterinarian page")
    public void beforeClass() {
        veterinarian = new Veterinarian();
        veterinariansPage = new VeterinariansPage(driver);
        veterinariansPage.openPage();
        newVeterinarianPage = veterinariansPage.clickAddVetButton();
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Step("Add New Veterinarian")
    public void addNewVeterinarianTest() {
        veterinarian.setFirstName("Simple");
        veterinarian.setLastName("User");
        veterinarian.setType();


        newVeterinarianPage.fillVeterinarian(veterinarian);
        newVeterinarianPage.clickSaveVetButton();
        goToVetsPage();
        veterinariansPage.verifyVetNewelyAddedPresentinList(veterinarian);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'First name' should be {isMoreThan}")
    public void VerifyFirstNameLength() {
        newVeterinarianPage.setFirstName("1");
        assertThat(newVeterinarianPage.errorMessage(firstName + isMoreThan).isDisplayed()).isTrue();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'Last name' should be {isMoreThan}")
    public void VerifyLastNameLength() {
        newVeterinarianPage.setLastName("1");
        assertThat(newVeterinarianPage.errorMessage(lastName + isMoreThan).isDisplayed()).isTrue();
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'First name' is required field")
    public void VerifyFirstNameRequired() {
        newVeterinarianPage.clearField("firstName");
        assertThat(newVeterinarianPage.errorMessage(firstName + isRequired).isDisplayed()).isTrue();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'Last name' is required field")
    public void VerifyLastNameRequired() {
        newVeterinarianPage.clearField("lastName");
        assertThat(newVeterinarianPage.errorMessage(lastName + isRequired).isDisplayed()).isTrue();
    }


}
