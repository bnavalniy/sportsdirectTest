package com.sportsdirect;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(id ="topMenu")
    WebElement menu;

    //@FindBy(xpath = "//a[contains(text(),'Mens')]")
    @FindBy(css = "#topMenu > ul > li > div > div > ul > li:nth-child(1) > a")

    WebElement mans;

    @FindBy(xpath = "//*[@id='dnn_ctr17843214_HtmlModule_lblContent']/div/div/div[2]/div[1]/ul[2]/li[17]/a")
    WebElement shoes;

    public void openMansShoes(){
        mans.click();
        shoes.click();
    }
}