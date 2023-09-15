package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";
    private final By loginPageHeader = By.xpath(".//h2[text()='Вход']");
    private final By emailInput = By.xpath("//form/fieldset[1]//input");
    private final By passwordInput = By.xpath("//form/fieldset[2]//input");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmailInput(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPasswordInput(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public void waitLoginPageIsOpen(){
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf(driver.findElement(loginPageHeader)));
    }
}
