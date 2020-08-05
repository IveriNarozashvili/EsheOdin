package Test;

import managers.InitManager;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    @Before
    public void setUp(){
        InitManager.initFramework();
    }

    @After
    public void quitFramework(){
        InitManager.quitFramework();
    }
}
