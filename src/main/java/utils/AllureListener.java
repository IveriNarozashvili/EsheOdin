package utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.junit4.AllureJunit4;
import managers.DriverManager;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureListener extends AllureJunit4 {

    @Override
    public void testFailure(final Failure failure) {
        addScreenShot();
        super.testFailure(failure);
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] addScreenShot() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
