package com.ProjectCC.dero.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
@Setter
public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "loginForm")
    private WebElement loginForm;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div")
    private WebElement failedLoginAlert;

    public LoginPage() {}

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed(){
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.elementToBeClickable(loginForm));
    }

    public void ensureAlertIsDisplayed() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(failedLoginAlert));

    }

}
