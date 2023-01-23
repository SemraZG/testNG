package techproed.tests.listeners;

import org.testng.Assert;
import org.testng.annotations.Test;
import techproed.utilities.Driver;

public class ListenersTest5 {

    @Test
    public void test1(){
        Driver.getDriver().get("https://techproeducation.com/");
        Assert.assertTrue(false);
    }

}
