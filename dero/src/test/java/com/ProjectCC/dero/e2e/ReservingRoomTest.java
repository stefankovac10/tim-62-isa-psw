package com.ProjectCC.dero.e2e;

import com.ProjectCC.dero.pages.CadminProfilePage;
import com.ProjectCC.dero.pages.LoginPage;
import com.ProjectCC.dero.pages.RequestsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class ReservingRoomTest {

    private WebDriver browser;

    private LoginPage loginPage;
    private CadminProfilePage cadminProfilePage;
    private RequestsPage requestsPage;


    @BeforeMethod
    public void setUp() {
        // instantiate chrome browser
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        browser = new ChromeDriver();

        //maximize window
        browser.manage().window().maximize();

        //navigate
        browser.navigate().to("http://localhost:8081/login");

        loginPage = PageFactory.initElements(browser, LoginPage.class);
        cadminProfilePage = PageFactory.initElements(browser, CadminProfilePage.class);
        requestsPage = PageFactory.initElements(browser, RequestsPage.class);
    }

    @Test
    public void testLoggingIn() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputEmail().sendKeys("zika@gmail.com");
        loginPage.getInputPassword().sendKeys("asdf");

        loginPage.getLoginButton().click();

        cadminProfilePage.ensureItDisplayed();
        assertEquals("http://localhost:8081/cadmin/profile", browser.getCurrentUrl());
    }

    @Test
    public void testFailedLoggingIn() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputEmail().sendKeys("nema_me_u_bazi@gmail.com");
        loginPage.getInputPassword().sendKeys("asdf");

        loginPage.getLoginButton().click();

        loginPage.ensureIsDisplayed();
        loginPage.ensureAlertIsDisplayed();
        assertEquals("http://localhost:8081/login", browser.getCurrentUrl());
    }

    @Test
    public void testViewingAvailableRequests() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputEmail().sendKeys("zika@gmail.com");
        loginPage.getInputPassword().sendKeys("asdf");

        loginPage.getLoginButton().click();
        cadminProfilePage.ensureButtonIsVisible();
        cadminProfilePage.getRequestsButton().click();
        cadminProfilePage.getRouteToExaminations().click();

        requestsPage.ensureIsDisplayed();
        requestsPage.ensureTableIsDisplayed();
        assertEquals("http://localhost:8081/cadmin/erRequests", browser.getCurrentUrl());
    }

    @Test
    public void testViewingNoRequestsMessage() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputEmail().sendKeys("jova@gmail.com");
        loginPage.getInputPassword().sendKeys("asdf");

        loginPage.getLoginButton().click();
        cadminProfilePage.ensureButtonIsVisible();
        cadminProfilePage.getRequestsButton().click();
        cadminProfilePage.getRouteToExaminations().click();

        requestsPage.ensureIsDisplayed();
        requestsPage.ensureTableIsNotDisplayed();
        assertEquals("http://localhost:8081/cadmin/erRequests", browser.getCurrentUrl());
    }

    @Test
    public void testFindingAvailableRooms() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputEmail().sendKeys("zika@gmail.com");
        loginPage.getInputPassword().sendKeys("asdf");

        loginPage.getLoginButton().click();
        cadminProfilePage.ensureButtonIsVisible();
        cadminProfilePage.getRequestsButton().click();
        cadminProfilePage.getRouteToExaminations().click();

        requestsPage.ensureIsDisplayed();
        requestsPage.ensureTableIsDisplayed();
        requestsPage.getSecondRequestElement().click();

        requestsPage.ensureTableWithRequestsIsVisible();
        assertEquals("http://localhost:8081/cadmin/erRequests", browser.getCurrentUrl());
    }

    @Test
    public void testReservingFirstRoomForFirstRequest() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputEmail().sendKeys("zika@gmail.com");
        loginPage.getInputPassword().sendKeys("asdf");

        loginPage.getLoginButton().click();
        cadminProfilePage.ensureButtonIsVisible();
        cadminProfilePage.getRequestsButton().click();
        cadminProfilePage.getRouteToExaminations().click();

        requestsPage.ensureIsDisplayed();
        requestsPage.ensureTableIsDisplayed();
        requestsPage.getFirstRequestElement().click();

        requestsPage.ensureButtonIsVisible();
        requestsPage.getFirstRoomReserveButton().click();


        requestsPage.ensureTableIsDisplayed();
        requestsPage.ensureTableWithRoomsIsNotWisible();
        assertEquals("http://localhost:8081/cadmin/erRequests", browser.getCurrentUrl());
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
    }
}
