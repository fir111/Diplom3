package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.helper.UserData;
import praktikum.pages.*;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConstructorTest {
    private WebDriver driver;

    @DisplayName("Настройка теста")
    @Description("Настройка веб-драйвера, регистрация, логин и переход в личный кабинет")
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        registrationPage.waitRegistrationPageIsOpen();

        String email = UserData.getEmail();
        registrationPage.setNameInput(UserData.NAME);
        registrationPage.setEmailInput(email);
        registrationPage.setPasswordInput(UserData.PASSWORD);
        registrationPage.clickRegistrationButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageIsOpen();
        loginPage.setEmailInput(email);
        loginPage.setPasswordInput(UserData.PASSWORD);
        loginPage.clickLoginButton();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitMainPageIsOpen();
        mainPage.clickPersonalAccount();

        new PersonalAccountPage(driver).waitPersonalAccountPageIsOpen();
    }

    @DisplayName("Завершение теста")
    @Description("Выключение веб-драйвера")
    @After
    public void tearDown() {
        driver.quit();
    }

    @DisplayName("Проверка перехода по клику на Конструктор")
    @Description("Переход по клику на Конструктор ведет на главную страницу")
    @Test
    public void clickConstructorLeadsToMainPage() {
        HeaderElement headerElement = new HeaderElement(driver);
        headerElement.clickConstructor();

        new MainPage(driver).waitMainPageIsOpen();
        String actualUrl = driver.getCurrentUrl();
        assertThat(actualUrl, is(MainPage.URL));
    }

    @DisplayName("Проверка перехода по клику на Лого")
    @Description("Переход по клику на Лого ведет на главную страницу")
    @Test
    public void clickLogoLeadsToMainPage() {
        HeaderElement headerElement = new HeaderElement(driver);
        headerElement.clickLogo();

        new MainPage(driver).waitMainPageIsOpen();
        String actualUrl = driver.getCurrentUrl();
        assertThat(actualUrl, is(MainPage.URL));
    }
}