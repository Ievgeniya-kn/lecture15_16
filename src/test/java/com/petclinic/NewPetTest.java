package com.petclinic;

import com.petclinic.pageobject.PetsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class NewPetTest extends TestBase {

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
    public void verifyAddNewPet()    {
        petsPage.setName("Shark");
        petsPage.clickSavePetButton();
        assertFalse(petsPage.verifySaveBtnIsDisabled());
    }

    @Test
    public void verifyPetNameIsRequired()    {
        petsPage.setName("");
        petsPage.clickSavePetButton();
        assertTrue(petsPage.verifySaveBtnIsDisabled());
    }

}
