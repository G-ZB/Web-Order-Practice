package com.academy.techcenture.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class Product extends Base{

    public Product(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this); // this line will initialize all elements
    }

    @FindBy(tagName = "h2")
    private WebElement listOfProductHeader;

    @FindBy(xpath = "//table[@class='ProductsTable']")
    private WebElement productsTable;

    public void verifyProductTable() {

        String[][] prductInfo = {
                {"MyMoney", "$100", "8%"},
                {"FamilyAlbum", "$80", "15%"},
                {"ScreenSaver", "$20", "10%"}
        };

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='ProductsTable']/tbody/tr"));
        rows.size();
        List<WebElement> columns = driver.findElements(By.xpath("//table[@class='ProductsTable']/tbody/tr[1]/td"));
        columns.size();

        for (int i = 1; i < rows.size(); i++) {
            for (int j = 1; j <= columns.size(); j++) {
                WebElement cell = driver.findElement(By.xpath("//table[@class='ProductsTable']/tbody/tr[" + (i + 1) + "]/td[" + j + "]"));
                String actualCellData = cell.getText();
                String expectedCellData = prductInfo[i - 1][j];
                Assert.assertEquals(actualCellData, expectedCellData, "Cell Data not matching");


            }

        }
    }

}
