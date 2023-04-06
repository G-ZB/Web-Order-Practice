package com.academy.techcenture.pages;

import com.academy.techcenture.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ViewOrders extends Base{

    public ViewOrders(WebDriver driver)  {
        super(driver);
        PageFactory.initElements(driver, this); // this line will initialize all elements
    }


    //    find web elements
    @FindBy(xpath = "//h2[contains(text(),'Lists of All Orders')]")
    private WebElement listOfAllOrdersHeaders;

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    private WebElement checkAllOrdersBtn;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    private WebElement uncheckAllOrdersBtn;

    @FindBy(id = "ctl00_MainContent_orderGrid")
    private WebElement ordersTable;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    private WebElement deleteSelectedBtn;

    @FindBy(xpath = "//input[starts-with(@id,'ctl00_MainContent_orderGrid_ctl0')]")
    private List<WebElement> orders;


    //    user actions/Methods
    public void checkAllOrders() {
        //this method should check all orders on the order tables

        assertTrue(checkAllOrdersBtn.isEnabled(), "check all order btn is not enabled");
        checkAllOrdersBtn.click();
        for (int i = 0; i < orders.size(); i++) {
            assertTrue(orders.get(i).isSelected(), "Order" + (i + 1) + "failed");
        }

    }

    public void uncheckAllOrders() {
        //this method should uncheck all orders on the order tables

        assertTrue(uncheckAllOrdersBtn.isEnabled(), "uncheck all order btn is not enabled");
        uncheckAllOrdersBtn.click();
        for (int i = 0; i < orders.size(); i++) {
            assertTrue(orders.get(i).isSelected(), "Order" + (i + 1) + "failed");

        }

    }

    public void deleteSelectedOrders() {
        //this method should delete check orders on the order tables
        //Select random records in the orders

        int ordersSize = orders.size();
        int randomSelect = CommonUtils.generateRandomNumber(2, 9);
        WebElement randomInput = driver.findElement(By.xpath("//input[@id = 'ctl00_MainContent_orderGrid_ctl0" + randomSelect+ "_OrderSelector']"));
        randomInput.click();
        assertTrue(deleteSelectedBtn.isEnabled(), "delete selected btn is not enabled");
        deleteSelectedBtn.click();
        assertTrue(orders.size() == ordersSize - 1, "order was not deleted");

    }

    public void verifyNewOrderIsPlaced(){



    }
}
