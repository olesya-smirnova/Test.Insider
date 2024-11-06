package com.insider.Insider2.QaPageCases;


import com.insider.Insider2.BaseWebQaPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckQaPageWebTest extends BaseWebQaPage {


    private QaPageActions qapageActions;
    
    @BeforeEach
    public void setUp() {
        // Initialize CareersActions with the driver
    	qapageActions = new QaPageActions(driver);
    }

    @Order(1)
    @Test
    void checkQaPage() {
    	qapageActions.navigateToQaPage();
    }

    @Order(2)
    @Test
    void checkQafilter() {
    	qapageActions.navigateToQaPage();
    	qapageActions.validFilterQa();
    }
    
    @Order(3)
    @Test
    void checkCountryfilter() {
    	qapageActions.navigateToQaPage();
    	qapageActions.validFilterCountry();
    }
    
    @Order(4)
    @Test
    void checkPositioninList() {
    	qapageActions.navigateToQaPage(); 
        qapageActions.validPosition();
    }
    
    @Order(5)
    @Test
    void checkRolepage() {
    	qapageActions.navigateToQaPage();
    	qapageActions.validRolepage();
    }
    
    
   
}