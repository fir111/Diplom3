package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final By forgotPasswordPageHeader = By.xpath("//h2[text()='Восстановление пароля']");
    private final By loginLink = By.xpath("//*[@id='root']//p/a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(){
        driver.get(URL);
    }

    public void clickLoginLink(){
        driver.findElement(loginLink).click();
    }

    public void waitForgotPasswordPageIsOpen(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(forgotPasswordPageHeader)));
    }
}
