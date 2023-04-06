package com.academy.techcenture;

import com.academy.techcenture.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class DataDrivenTest {


    @Test(dataProvider = "webOrders")
    public void ordersTest(HashMap<String,String> data){

        String product = data.get("Product");
        String quantity = data.get("Quantity");
        String street = data.get("Street");
        String city = data.get("City");
        String state = data.get("State");
        String zip = data.get("Zip");
        String card = data.get("Card");
        String cardNumber = data.get("CardNumber");
        String expDate = data.get("ExpDate");

        System.out.println(product + " " + quantity + " " + street + " " + city + " " + state + " " + zip
                + " " + card + " " + cardNumber + " " + expDate);
    }

    @DataProvider(name = "webOrders")
    public Object[][] getWebOrderData(){
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/weborders.xlsx", "orders");
        Object[][] data = excelReader.getData();
        return data;

    }

}
