package com.sportsdirect;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoesPage {

    @FindBy(xpath="//*[@id='dnn_ctr17842895_ViewTemplate_ctl00_ctl10_lstFilters_productFilterList_0']/div[2]/a/span")
    WebElement brandFiretrap;

    @FindBy(xpath = "//*[@id='dnn_ctr17842895_ViewTemplate_ctl00_ctl10_lstFilters_productFilterList_0']/div[4]/a/span")
    WebElement brandScechers;

    @FindBy(xpath = "//*[@id='PriceFilterTextEntryMin']")
    WebElement minPrice;

    @FindBy(xpath = "//*[@id='PriceFilterTextEntryMax']")
    WebElement maxPrice;

    @FindBy(xpath = "//*[@id='PriceFilterTextEntryApply']")
    WebElement applyFilter;

    public void choseBrand(){
        brandFiretrap.click();
        brandScechers.click();
    }

    public void choosePrice(String min,String max){
        minPrice.sendKeys(min);
        maxPrice.sendKeys(max);
    }

    public void applyFilter() {
        applyFilter.click();
    }
}