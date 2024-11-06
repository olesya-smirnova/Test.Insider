package com.insider.Insider2.CareersCases;

import com.insider.Insider2.BaseWeb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.testng.Reporter;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckCareersWebTest extends BaseWeb {

    private CareersActions careersActions;
    
    @BeforeEach
    public void setUp() {
        // Initialize CareersActions with the driver
        careersActions = new CareersActions(driver);
    }

    @Order(1)
    @Test
    void checkCareersTest() {
        careersActions.navigateToCareersPage();
    }

    @Order(2)
    @Test
    void checkCareersLocationsTest() {
        careersActions.navigateToCareersPage();
        careersActions.verifyLocationSlider();  
        System.out.println("Verified location slider.");
    }

    @Order(3)
    @Test
    void checkCareersTeamsTest() {
        careersActions.navigateToCareersPage();
        careersActions.verifyTeamsBlock();    
        System.out.println("Verified teams block.");
    }

    @Order(4)
    @Test
    void checkLifeInsiderTest() {
        careersActions.navigateToCareersPage();
        careersActions.verifyLifeAtInsiderBlock();
        System.out.println("Verified 'Life at Insider' block.");
        
    }
    

    @Order(5)
    @Test
    void checkQArolepage() {
        careersActions.navigateToCareersPage();
        careersActions.verifyLifeAtInsiderBlock();
        System.out.println("Verified QA role page.");
    }
    
   
}