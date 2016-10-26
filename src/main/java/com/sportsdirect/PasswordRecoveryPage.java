package com.sportsdirect;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordRecoveryPage {

    @FindBy(xpath = "//*[@id='dnn_ctr17843022_PasswordReset_UserName']")
    private WebElement email;

    @FindBy(xpath = "//*[@id='dnn_ctr17843022_PasswordReset_cmdSendPassword']")
    private WebElement sendEmail;

    public void setEmail(String mail){
        email.sendKeys(mail);
    }

    public void sendEmail(){
        sendEmail.click();
    }
}