package pages;

import io.qameta.allure.Step;
import managers.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    @Step("выбираем главное меню {nameBaseMenu}")
    public DepositPage selectBaseMenu(String nameBaseMenu) {
        try {
            DriverManager.getDriver()
                    .findElement(By.xpath("//div[@class='service']//div[text()='"+nameBaseMenu+"']/../a[not(@class)]"))
                    .click();
        }catch (NoSuchElementException exception){
            Assert.fail("Элемент '" + nameBaseMenu + "' не был найден на странице!");
        }
        return app.getDepositPage();
    }

}

