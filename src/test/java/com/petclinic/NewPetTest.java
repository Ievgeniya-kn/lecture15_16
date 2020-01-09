package com.petclinic;

import com.petclinic.pageobject.BasePage;
import com.petclinic.pageobject.OwnersPage;
import com.petclinic.pageobject.PetsPage;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;

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
        pet = new Pet();
        pet.setName("Cat");

        petsPage.clickSavePetButton();
        assertFalse(petsPage.verifySaveBtnIsEnabled());
    }

    @Test
    public void verifyPetNameIsRequired()    {
        pet = new Pet();
        pet.setName("");
        petsPage.clickSavePetButton();
        assertTrue(petsPage.verifySaveBtnIsEnabled());
    }

}
