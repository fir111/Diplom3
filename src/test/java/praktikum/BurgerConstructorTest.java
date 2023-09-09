package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import praktikum.pages.*;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BurgerConstructorTest {
    private WebDriver driver;
    private final By activeElement =
            By.xpath("//*[@id='root']//*[contains(@class,'tab_tab_type_current')]/span");

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

    @DisplayName("Проверка таба Булки")
    @Description("Таб Булки активный по умолчанию")
    @Test
    public void bunTabIsActiveByDefault() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.waitMainPageIsOpen();

        String actualTabText = driver.findElement(activeElement).getText();
        String expectedTabText = "Булки";
        assertThat(actualTabText, is(expectedTabText));
    }

    @DisplayName("Проверка таба Соусы")
    @Description("Клик по табу Соусы делает его активным ")
    @Test
    public void sauceTabClickMakeItActive() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.waitMainPageIsOpen();

        mainPage.clickSauceTab();

        String actualTabText = driver.findElement(activeElement).getText();
        String expectedTabText = "Соусы";
        assertThat(actualTabText, is(expectedTabText));
    }

    @DisplayName("Проверка таба Начинки")
    @Description("Клик по табу Начинки делает его активным ")
    @Test
    public void fillingTabClickMakeItActive() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.waitMainPageIsOpen();

        mainPage.clickFillingTab();

        String actualTabText = driver.findElement(activeElement).getText();
        String expectedTabText = "Начинки";
        assertThat(actualTabText, is(expectedTabText));
    }
}
