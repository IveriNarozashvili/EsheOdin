package Test;


import io.qameta.allure.junit4.DisplayName;
import managers.ManagerPages;
import org.junit.Test;

public class FirstTest extends BaseTest {


    @DisplayName("Расчет доходности вклада")
    @Test
    public void selectIpoteka() {
        ManagerPages.getManagerPages().getStartPage()
                .selectBaseMenu("Вклады")
                .vibodValyuti("Рубли")
                .fillField("Сумма вклада", 300000)
                .selectElementFill("Срок", "6 месяцев")
                .fillField("Ежемесячное пополнение", 50000)
                .selectCheckBox("Ежемесячная капитализация", true)
                .checkResult("Начислено %", "7 421,56")
                .checkResult("Пополнение за 6 месяцев", "250 000")
                .checkResult("К снятию через 6 месяцев", "557 422,56");

    }

}

