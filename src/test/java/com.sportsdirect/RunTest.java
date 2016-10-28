package com.sportsdirect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class RunTest {

    private WebDriver driver;
    private String url;
    private Actions action;
    private HomePage homePage;
    Mail mail;

    @Before
    public void start(){
        System.setProperty("webdriver.chrome.driver","C:/Users/Irina/Desktop/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        url = "http://www.sportsdirect.com/";
        driver.get(url);
    }

    @Test
    public void filterTest() throws InterruptedException {
        ShoesPage shoesPage = PageFactory.initElements(driver, ShoesPage.class);
        FilterResult filterResult = PageFactory.initElements(driver, FilterResult.class);

        navigateToMansShoes();
        shoesPage.choseBrand();
        shoesPage.choosePrice("30", "60");
        shoesPage.applyFilter();
        filterResult.verifyBrand("Skechers", "Firetrap");
        filterResult.verifyPrice(30, 60);
    }

    @Test
    public void passwordRecoveryTest() throws InterruptedException {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        PasswordRecoveryPage passwordRecoveryPage = PageFactory.initElements(driver, PasswordRecoveryPage.class);
        NewPasswordPage newPasswordPage = PageFactory.initElements(driver, NewPasswordPage.class);
        mail = new Mail();
        driver.get("https://lv.sportsdirect.com/Login?returnurl=/");
        String newPassword = "q1w2e3r*";
        String email = "tsportman880@gmail.com";
        loginPage.navigateToPassRecovery();
        passwordRecoveryPage.setEmail(email);
        passwordRecoveryPage.sendEmail();
        driver.get(mail.getUrlRecovery());
        newPasswordPage.setNewCredentials(email, newPassword);
        newPasswordPage.confirm();
        newPasswordPage.checkConfirmationText();
    }
    @After
    public void after(){
       driver.close();
    }

    public void navigateToMansShoes() throws InterruptedException {
        homePage = PageFactory.initElements(driver,HomePage.class);
        action = new Actions(driver);
        homePage.menu = driver.findElement(By.id("topMenu"));
        Thread.sleep(1000);
        action.moveToElement(homePage.menu).build().perform();
        Thread.sleep(2000);
        homePage.openMansShoes();
        Thread.sleep(2000);
    }
}