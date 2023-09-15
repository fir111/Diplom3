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
import praktikum.pages.ForgotPasswordPage;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest {

    private WebDriver driver;
    private String email;

    @DisplayName("Настройка теста")
    @Description("Настройка веб-драйвера и регистрация пользователя")
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        registrationPage.waitRegistrationPageIsOpen();

        email = UserData.getEmail();
        registrationPage.setNameInput(UserData.NAME);
        registrationPage.setEmailInput(email);
        registrationPage.setPasswordInput(UserData.PASSWORD);
        registrationPage.clickRegistrationButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageIsOpen();
    }

    @DisplayName("Завершение теста")
    @Description("Выключение веб-драйвера")
    @After
    public void tearDown()  {
        driver.quit();
    }

    @DisplayName("Проверка логина с главной страницы")
    @Description("Позитивный сценарий - логин с корректными данными прошел успешно")
    @Test
    public void loginWithLoginButtonIsSuccessful() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.waitMainPageIsOpen();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageIsOpen();
        loginPage.setEmailInput(email);
        loginPage.setPasswordInput(UserData.PASSWORD);
        loginPage.clickLoginButton();

        new MainPage(driver).waitMainPageIsOpen();
        String actualUrl = driver.getCurrentUrl();
        assertThat(actualUrl, is(MainPage.URL));
    }


    @DisplayName("Проверка логина по нажатию ссылки Войти")
    @Description("Позитивный сценарий - логин с корректными данными по нажатию ссылки Войти " +
            "в форме регистрации прошел успешно")
    @Test
    public void loginByClickLoginHrefIsSuccessful() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        registrationPage.waitRegistrationPageIsOpen();
        registrationPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageIsOpen();
        loginPage.setEmailInput(email);
        loginPage.setPasswordInput(UserData.PASSWORD);
        loginPage.clickLoginButton();

        new MainPage(driver).waitMainPageIsOpen();
        String actualUrl = driver.getCurrentUrl();
        assertThat(actualUrl, is(MainPage.URL));
    }

    @DisplayName("Проверка логина по нажатию кнопки Личный кабинет")
    @Description("Позитивный сценарий - логин с корректными данными прошел успешно")
    @Test
    public void loginWithPersonalAccountButtonIsSuccessful() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.waitMainPageIsOpen();
        mainPage.clickPersonalAccount();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageIsOpen();
        loginPage.setEmailInput(email);
        loginPage.setPasswordInput(UserData.PASSWORD);
        loginPage.clickLoginButton();

        new MainPage(driver).waitMainPageIsOpen();
        String actualUrl = driver.getCurrentUrl();
        assertThat(actualUrl, is(MainPage.URL));
    }

    @DisplayName("Проверка логина со страницы восстановления Пароля")
    @Description("Позитивный сценарий - успешный логин с корректными данными по нажатию ссылки Войти " +
            "со страницы восстановления Пароля")
    @Test
    public void loginViaForgottenPasswordPageIsSuccessful() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openPage();
        forgotPasswordPage.waitForgotPasswordPageIsOpen();
        forgotPasswordPage.clickLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPageIsOpen();
        loginPage.setEmailInput(email);
        loginPage.setPasswordInput(UserData.PASSWORD);
        loginPage.clickLoginButton();

        new MainPage(driver).waitMainPageIsOpen();
        String actualUrl = driver.getCurrentUrl();
        assertThat(actualUrl, is(MainPage.URL));
    }
}