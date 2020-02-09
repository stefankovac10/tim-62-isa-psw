package com.ProjectCC.dero.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class RequestsPage {

    private WebDriver driver;

    @FindBy(id = "requestsMF")
    private WebElement requestsHeader;

    @FindBy(id = "requestsTable")
    private WebElement requests;

    @FindBy(id = "noRequests")
    private WebElement noElementsMessage;

    @FindBy(id = "1row")
    private WebElement firstRequestElement;

    @FindBy(id = "2row")
    private WebElement secondRequestElement;

    @FindBy(id = "3row")
    private WebElement thirdRequestElement;

    @FindBy(id = "tableRoomsDates")
    private WebElement tableWithRooms;

    @FindBy(id = "1roomRow")
    private WebElement tableWithRoomsFirstElement;

    @FindBy(id = "2roomRow")
    private WebElement tableWithRoomsSecondElement;

    @FindBy(id = "3roomRow")
    private WebElement tableWithRoomsThirdElement;

    @FindBy(id = "1button")
    private WebElement firstRoomReserveButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div")
    private WebElement toastifyAlert;

    public RequestsPage() {}

    public RequestsPage(WebDriver driver) { this.driver = driver; }

    public void ensureIsDisplayed(){
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(requestsHeader));
    }

    public void ensureTableIsDisplayed() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(requests));
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(secondRequestElement));
    }

    public void ensureTableIsNotDisplayed() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(noElementsMessage));
    }

    public void ensureTableWithRequestsIsVisible() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(tableWithRooms));
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(tableWithRoomsThirdElement));
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(thirdRequestElement));
    }

    public void ensureTableWithRoomsIsNotWisible() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(toastifyAlert));
    }

    public void ensureButtonIsVisible() {
        (new WebDriverWait(driver, 20))
            .until(ExpectedConditions.visibilityOf(firstRoomReserveButton));

    }
}
