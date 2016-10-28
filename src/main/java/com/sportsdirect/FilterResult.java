package com.sportsdirect;

import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.google.common.base.CharMatcher;

import java.util.List;

public class FilterResult {

    @FindAll(@FindBy(how = How.XPATH, using = "//*[contains(@class,'productdescriptionbrand')]"))
    List<WebElement> brands;

    @FindAll(@FindBy(how = How.XPATH, using = "//*[contains(@class,'CurrencySizeLarge curprice productHasRef')]"))
    List<WebElement> prices;

    public void verifyBrand(String brand1, String brand2) throws InterruptedException {
        String actualBrand = "";

        for (int i = 0;i< brands.size();i++) {
            actualBrand = brands.get(i).getAttribute("innerHTML");
            Assert.assertTrue("Different brands: actual = " + actualBrand + ", expected = " + brand1 + " or " + brand2,
                    actualBrand.equals(brand1) || actualBrand.equals(brand2));
        }
    }

    public void verifyPrice(int minPrice, int maxPrice) {
        int actualPrice = 0;
        minPrice = minPrice*100;
        maxPrice = maxPrice*100;

        for (int i = 0;i< prices.size();i++) {
            actualPrice = Integer.parseInt(CharMatcher.DIGIT.retainFrom(prices.get(i).getAttribute("innerText")));
            Assert.assertTrue("Price " + actualPrice + " is not in range: " + minPrice + " - " + maxPrice,
                    actualPrice >= minPrice && actualPrice <= maxPrice);
        }
    }
}