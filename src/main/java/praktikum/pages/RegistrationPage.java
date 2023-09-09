package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver driver;
    private static final String URL = "https://stellarburgers.nomoreparties.site/register";
    private final By registrationPageHeader = By.xpath(".//h2[text()='Регистрация']");
    private final By nameInput = By.xpath("//form/fieldset[1]//input");
    private final By emailInput = By.xpath("//form/fieldset[2]//input");
    private final By passwordInput = By.xpath("//form/fieldset[3]//input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//div[@id='root']//main//p/a[@href='/login']");
    private final By incorrectPasswordWarning =
            By.xpath("//form/fieldset[3]//p[contains(@class,'input__error')]");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(){
        driver.get(URL);
    }

    public void setNameInput(String name){
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setEmailInput(String email){
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPasswordInput(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickRegistrationButton(){
        driver.findElement(registerButton).click();
    }

    public void clickLoginLink()
    {
        driver.findElement(loginLink).click();
    }
    public String getIncorrectPasswordWarning(){
        return driver.findElement(incorrectPasswordWarning).getText();
    }

    public void waitRegistrationPageIsOpen(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(registrationPageHeader)));
    }
}
