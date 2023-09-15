package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderElement {
    private final WebDriver driver;
    private final By constructor =
            By.xpath("//*[@id='root']//header/nav/ul/li[1]/a/p[text()='Конструктор']");
    private final By logo = By.xpath("//*[@id='root']/div/header/nav/div/a[@href='/']");

    public HeaderElement(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConstructor(){
        driver.findElement(constructor).click();
    }

    public void clickLogo(){
        driver.findElement(logo).click();
    }
}
