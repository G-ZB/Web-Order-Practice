package com.academy.techcenture;

import com.academy.techcenture.pages.Login;
import com.academy.techcenture.pages.PlaceOrder;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest{


    @Test
    public void  PlaceOrderTest() throws InterruptedException{
        Login loginPage = new Login(driver);
        loginPage.navigateToLoginPage();
        loginPage.login();

        PlaceOrder placeOrderPage = new PlaceOrder(driver);
        placeOrderPage.clickOnOrderLink();
        placeOrderPage.headersDisplay();
        placeOrderPage.placeNewOrder();
        placeOrderPage.validateOrderPlaced();
        placeOrderPage.clickOnViewAllOrdersLink();

        Thread.sleep(3000);

        placeOrderPage.logout();

    }
}
