package praktikum;

import java.util.concurrent.TimeUnit;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.helper.UserData;
import praktikum.pages.LoginPage;
import praktikum.pages.RegistrationPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationPageTest {

    private WebDriver driver;
    private static final String INCORRECT_PASSWORD_WARNING_MESSAGE = "Некорректный пароль";

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

    @DisplayName("Проверка успешной регистрации")
    @Description("Позитивный сценарий - регистрация с корректными данными прошла успешно")
    @Test
    public void registrationIsSuccess() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        registrationPage.waitRegistrationPageIsOpen();

        String email = UserData.getEmail();
        registrationPage.setNameInput(UserData.NAME);
        registrationPage.setEmailInput(email);
        registrationPage.setPasswordInput(UserData.PASSWORD);
        registrationPage.clickRegistrationButton();

        new LoginPage(driver).waitLoginPageIsOpen();
        String actualUrl = driver.getCurrentUrl();
        assertThat(actualUrl, is(LoginPage.URL));
    }

    @DisplayName("Проверка регистрации с некорректным паролем")
    @Description("Негативный сценарий - регистрация с некорректными паролем не выполнена")
    @Test
    public void registrationWithShortPasswordIsUnsuccessful() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openPage();
        registrationPage.waitRegistrationPageIsOpen();

        String email = UserData.getEmail();
        registrationPage.setNameInput(UserData.NAME);
        registrationPage.setEmailInput(email);
        registrationPage.setPasswordInput("123");
        registrationPage.clickRegistrationButton();

        String actualWarningText = registrationPage.getIncorrectPasswordWarning();
        assertThat(actualWarningText, is(INCORRECT_PASSWORD_WARNING_MESSAGE));
    }
}