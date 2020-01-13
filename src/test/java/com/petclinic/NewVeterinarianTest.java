package com.petclinic;

import com.petclinic.pageobject.NewOwnerPage;
import com.petclinic.pageobject.NewVeterinarianPage;
import com.petclinic.pageobject.OwnersPage;
import com.petclinic.pageobject.VeterinariansPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NewVeterinarianTest extends TestBase{
    VeterinariansPage veterinariansPage;
    Veterinarian veterinarian;
    NewVeterinarianPage newVeterinarianPage;
    String firstName= "First name";
    String lastName = "Last name";
    String type = "Type";
    String isRequired = " is required";
    String isMoreThan = " must be at least 2 characters long";


    @BeforeClass
    public void beforeClass() {
        veterinarian = new Veterinarian();
        veterinariansPage = new VeterinariansPage(driver);
        veterinariansPage.openPage();
        newVeterinarianPage = veterinariansPage.clickAddVetButton();
    }



    @Test
    public void addNewVeterinarianTest() {
        veterinarian.setFirstName("Simple");
        veterinarian.setLastName("User");
        veterinarian.setType();


        newVeterinarianPage.fillVeterinarian(veterinarian);
        veterinariansPage = newVeterinarianPage.clickAddVetButton();

        List<Veterinarian> veterinariansNames = veterinariansPage.getveterinariansList();
        assertThat(veterinariansNames).contains(veterinarian);
    }

    @Test
    public void VerifyFirstNameLength() {
        newVeterinarianPage.setFirstName("1");
        assertThat(newVeterinarianPage.errorMessage(firstName+isMoreThan).isDisplayed()).isTrue();
    }

    @Test
    public void VerifyLastNameLength() {
        newVeterinarianPage.setLastName("1");
        assertThat(newVeterinarianPage.errorMessage(lastName+isMoreThan).isDisplayed()).isTrue();
    }


    @Test
    public void VerifyFirstNameRequired() {
        newVeterinarianPage.clearField("firstName");
        assertThat(newVeterinarianPage.errorMessage(firstName+isRequired).isDisplayed()).isTrue();
    }

    @Test
    public void VerifyLastNameRequired() {
        newVeterinarianPage.clearField("lastName");
        assertThat(newVeterinarianPage.errorMessage(lastName+isRequired).isDisplayed()).isTrue();
    }


}
