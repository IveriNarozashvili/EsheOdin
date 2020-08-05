package pages;

import managers.ManagerPages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static managers.DriverManager.getDriver;


public class BasePage {

    protected ManagerPages app = ManagerPages.getManagerPages();


    protected Actions action = new Actions(getDriver());


    protected WebDriverWait wait = new WebDriverWait(getDriver(), 25, 5000);


    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }


    protected void fillInputField(WebElement field, String value) {
        action.click(field).build().perform();
        //field.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        wait.until(ExpectedConditions.textToBePresentInElement(field, ""));
        field.sendKeys(value);
    }

    protected void selectCheckBoxBase(WebElement checkBox, Boolean select) {
        if (select != checkBox.isSelected()) {
            checkBox.click();
        }
    }

    public static Boolean textToBePresentInElement(final WebElement element, final String text) {

        for (int i = 0; i < 5; i++) {

            String elementText = element.getText();
            if (elementText.contains(text)){
                return true;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
