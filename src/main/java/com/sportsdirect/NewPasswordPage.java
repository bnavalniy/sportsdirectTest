package com.sportsdirect;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPasswordPage {

    @FindBy(xpath = "//*[@id='dnn_ctr17843022_PasswordReset_txtEmailAddress']")
    private WebElement email;

    @FindBy(xpath = "//*[@id='dnn_ctr17843022_PasswordReset_txtNewPassword']")
    private WebElement newPassword;

    @FindBy(xpath = "//*[@id='dnn_ctr17843022_PasswordReset_txtConfirmNewPassword']")
    private WebElement confirmPassword;

    @FindBy(xpath = "//*[@id='dnn_ctr17843022_PasswordReset_lnkbtnChangePassword']")
    private WebElement confirmButton;

    @FindBy(xpath = "//*[@id='dnn_ctr17843022_PasswordReset_SuccessText']")
    private WebElement confirmationText;

    public void setNewCredentials(String mail, String password){
        email.sendKeys(mail);
        newPassword.sendKeys(password);
        confirmPassword.sendKeys(password);
    }

    public void confirm() throws InterruptedException {
        confirmButton.click();
        Thread.sleep(5000);
    }

    public void checkConfirmationText(){
        String actual = confirmationText.getText();
        String expected = "Your password has been successfully changed.";
        Assert.assertEquals(expected, actual);
    }
}