package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class DepositPage extends BasePage {

    @FindBy(xpath = "//span[@class='calculator__currency-field-text']")
    List<WebElement> selectValyuta;

    @FindBy(xpath = "(//div[@class='calculator__slide-input-field']//input)[1]")
    WebElement summaVklada;

    @FindBy(id = "period")
    WebElement srokVklada;

    @FindBy(xpath = "//input[@name='replenish']")
    WebElement ejemesyachnoePopolnenie;

    @FindBy(xpath = "//input[@name='capitalization']/..")
    WebElement ejemesyachnayaKapitalizaciya;

    @FindBy(xpath = "//span[@class='js-calc-earned']")
    WebElement checkProcent;

    @FindBy(xpath = "//span[@class='js-calc-replenish']")
    WebElement checkPopolnenie;

    @FindBy(xpath = "//span[@class='js-calc-result']")
    WebElement checkSnyatie;


    public DepositPage vibodValyuti(String value){
        for (WebElement item :selectValyuta) {
            if (item.getText().equals(value)){
                item.click();
                return this;
            }
        }
        Assert.fail("Не найдена валюта " + value);
        return this;
    }

    @Step("выбираем из выпадающего списка {nameSelect} значение {value}")
    public DepositPage selectElementFill(String nameSelect, String value){
        switch (nameSelect){
            case "Срок":
                Select srok = new Select(srokVklada);
                srok.selectByVisibleText(value);
                break;
            default:
                Assert.fail("Элемента " + nameSelect + "нет");
        }
        return this;
    }

    @Step("заполняем поле {nameField} значением {value}")
    public DepositPage fillField(String nameField, Integer value) {
        switch (nameField) {
            case "Сумма вклада":
                fillInputField(summaVklada, value.toString());
                break;
            case "Ежемесячное пополнение":
                fillInputField(ejemesyachnoePopolnenie, value.toString());
                break;
            default:
                Assert.fail("Поле '" + nameField + "' не существует!");
        }
        return this;
    }

    @Step("устанавливаем  {checkBoxName} в значение {select}")
    public DepositPage selectCheckBox(String checkBoxName, boolean select) {
        switch (checkBoxName) {
            case "Ежемесячная капитализация":
                selectCheckBoxBase(ejemesyachnayaKapitalizaciya, select);
                break;
            default:
                Assert.fail("Чекбокс '" + checkBoxName + "' не существует!");
        }
        return this;
    }
    @Step("проверяем что значение параметра {nameParam} равно {expectedValue}")
    public DepositPage checkResult(String nameParam, String expectedValue){
        switch (nameParam){
            case "Начислено %":
                Assert.assertTrue("Не совпало " + nameParam + "со значением " + expectedValue,
                        textToBePresentInElement(checkProcent, expectedValue));
                break;
            case "Пополнение за 6 месяцев":
                Assert.assertTrue("Не совпало " + nameParam + "со значением " + expectedValue,
                        textToBePresentInElement(checkPopolnenie, expectedValue));
                break;
            case "К снятию через 6 месяцев":
                Assert.assertTrue("Не совпало " + nameParam + "со значением " + expectedValue,
                        textToBePresentInElement(checkSnyatie, expectedValue));
                break;
            default:
                Assert.fail("Параметра '" + nameParam + "' не существует!");
        }
        return this;
    }
}
