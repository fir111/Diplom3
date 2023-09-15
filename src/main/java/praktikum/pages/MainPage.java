package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    private final By mainPageHeader = By.xpath("//h1[text()='Соберите бургер']");
    private final By personalAccount = By.xpath("//div[@id='root']//a[@href='/account']/p");
    private final By loginButton =
            By.xpath("//*[@id='root']//main/section[2]//button[text()='Войти в аккаунт']");

    private final By bunTab = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[1]");
     private final By sauceTab = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[2]");
    private final By fillingTab = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[3]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(){
        driver.get(URL);
    }

    public void clickPersonalAccount(){
        driver.findElement(personalAccount).click();
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void clickBunTab(){
        driver.findElement(bunTab).click();
    }

    public void clickSauceTab(){
        driver.findElement(sauceTab).click();
    }

    public void clickFillingTab(){
        driver.findElement(fillingTab).click();
    }

    public void waitMainPageIsOpen(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(mainPageHeader)));
    }

    public void waitTabIsSwitchedToFillings(){
        new WebDriverWait(driver, 3)
                .until(classContains(fillingTab));
    }

    public static ExpectedCondition<Boolean> classContains(final By locator) {
        return new ExpectedCondition<>() {
            private WebElement webElement;

            @Override
            public Boolean apply(WebDriver driver) {
                webElement = driver.findElement(locator);
                String classAttr = webElement.getAttribute("class");

                if (webElement != null) {
                    return classAttr.contains("tab_tab_type_current");
                }
                return false;
            }

            @Override
            public String toString() {
                return String.format("class to contain \"%s\". Current class: \"%s\"", locator, webElement);
            }
        };
    }
}
