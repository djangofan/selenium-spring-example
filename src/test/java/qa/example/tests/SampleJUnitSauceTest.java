package qa.example.tests;

import org.junit.Test;
import org.openqa.selenium.Platform;
import qa.example.framework.JUnitSauceTestBase;

import static org.junit.Assert.*;

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs .
 */
public class SampleJUnitSauceTest extends JUnitSauceTestBase
{
    public SampleJUnitSauceTest() {
        super(Platform.WINDOWS.name(), "33", "firefox");
    }

    @Test
    public void webDriverOne()
    {
        driver.get("http://www.amazon.com/");
        assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
    }

}

