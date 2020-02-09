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
public class CadminProfilePage {

    private WebDriver driver;

    @FindBy(id = "examinationRoomRequests")
    private WebElement routeToExaminations;

    @FindBy(id = "requestsNavButton")
    private WebElement requestsButton;

    @FindBy(id = "navBarCadmin")
    private WebElement navBarCadmin;

    public CadminProfilePage() {}

    public CadminProfilePage(WebDriver driver) { this.driver = driver; }

    public void ensureItDisplayed() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(navBarCadmin));
    }

    public void ensureButtonIsVisible() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(requestsButton));
    }

}
