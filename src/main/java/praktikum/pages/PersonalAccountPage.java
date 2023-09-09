package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    private final WebDriver driver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final By accountProfileLink =
            By.xpath("//*[@id='root']//main//nav/ul/li[1]/a[@href='/account/profile']");
    private final By logoutButton = By.xpath("//*[@id='root']//nav/ul/li[3]/button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }

    public void waitPersonalAccountPageIsOpen(){
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOf(driver.findElement(accountProfileLink)));
    }
}
