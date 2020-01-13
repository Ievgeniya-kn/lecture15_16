package com.petclinic;

import com.petclinic.pageobject.PetsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import static org.assertj.core.api.Assertions.assertThat;

public class NewPetTest extends TestBase {
    private String petName = "Shark";
    Pet pet;
    PetsPage petsPage;


    @BeforeClass
    public void beforeClass() {
        pet = new Pet();
        petsPage = new PetsPage(driver);
        petsPage.openPage();
        petsPage.clickAddPetButton();
    }


    @Test
    public void verifyAddNewPet() {
        petsPage.addPet(petName);
        assertFalse(petsPage.verifySaveBtnIsDisabled());
        assertThat(petsPage.getPetTypeList().contains(petName));
    }

    @Test
    public void verifyPetNameIsRequired() {
        petsPage.addPet("");
        assertTrue(petsPage.verifySaveBtnIsDisabled());
    }


    @Test
    public void petNameDeletePetByName() {
        petsPage.pressDeletePet(petName);
        assertFalse(petsPage.getPetTypeList().contains(petName));
    }

}
