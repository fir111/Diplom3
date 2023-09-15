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

public class LogoutTest {

    private WebDriver driver;

    @DisplayName("Настройка теста")
    @Description("Настройка веб-драйвера")
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS);
    }

    @DisplayName("Завершение теста")
    @Description("Выключение веб-драйвера")
    @After
    public void tearDown() {
        driver.quit();
    }

    @DisplayName("Проверка клика кнопки Логаут")
    @Description("Переход по клику на кнопку Логаут ведет на главную страницу")
    @Test
    public void clickLogoutButtonLeadsLoginPage() {
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

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.waitPersonalAccountPageIsOpen();

        personalAccountPage.clickLogoutButton();

        new LoginPage(driver).waitLoginPageIsOpen();
        String actualUrl = driver.getCurrentUrl();
        assertThat(actualUrl, is(LoginPage.URL));

    }
}