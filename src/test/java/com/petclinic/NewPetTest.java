package com.petclinic;

import com.petclinic.pageobject.PetsPage;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Pet type")
public class NewPetTest extends TestBase {
    private String petName = "Shark";
    Pet pet;
    PetsPage petsPage;


    @BeforeClass
    @Step("Open Pet page")
    public void beforeClass() {
        pet = new Pet();
        petsPage = new PetsPage(driver);
        petsPage.openPage();
        petsPage.clickAddPetButton();
    }


    @Test
    @Step
    @Severity(SeverityLevel.CRITICAL)
    public void verifyAddNewPet() {
        petsPage.addPet(petName);
        assertFalse(petsPage.verifySaveBtnIsDisabled());
        assertThat(petsPage.getPetTypeList().contains(petName));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Validation fields upon Creation")
    @Step("Verify 'Name' is required field")
    public void verifyPetNameIsRequired() {
        petsPage.ifButtonDisabledPressAdd();
        petsPage.addPet("");
        assertFalse(petsPage.verifySaveBtnIsDisabled());
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify pet type with name = {petName} is deleted")
    public void petNameDeletePetByName() {
        petsPage.pressDeletePet(petName);
        assertFalse(petsPage.getPetTypeList().contains(petName));
    }

}
