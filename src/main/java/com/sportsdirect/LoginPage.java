package com.sportsdirect;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(xpath = "//*[@id='dnn_ctr17842882_Login_Login_AuthenticationProvider_registerLogin_cmdForgottenPassword']")
    private WebElement forgotPass;

    public void navigateToPassRecovery(){
        forgotPass.click();
    }
}