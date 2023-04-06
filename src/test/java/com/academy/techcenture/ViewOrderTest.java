package com.academy.techcenture;

import com.academy.techcenture.pages.Login;
import com.academy.techcenture.pages.ViewOrders;
import org.testng.annotations.Test;

public class ViewOrderTest extends BaseTest{

    @Test
    public void checkAndUncheckAllOrders() throws InterruptedException {
        Login loginPage = new Login(driver);
        ViewOrders ordersPage = new ViewOrders(driver);

        loginPage.navigateToLoginPage();
        loginPage.login();

        ordersPage.clickOnViewAllOrdersLink();
        ordersPage.checkAllOrders();
        ordersPage.uncheckAllOrders();

        Thread.sleep(2000);
        ordersPage.logout();

    }


    @Test
    public void deleteOrderPositiveTest() throws InterruptedException {

        Login loginPage = new Login(driver);
        loginPage.navigateToLoginPage();
        loginPage.login();

        ViewOrders ordersPage = new ViewOrders(driver);
        ordersPage.deleteSelectedOrders();
        Thread.sleep(2000);
        ordersPage.logout();
    }


}
