package qa.example.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import qa.example.framework.JUnitSauceTestBase;

import static org.junit.Assert.*;

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs .
 */
public class SampleJUnitSauceTest extends JUnitSauceTestBase
{
    @Test
    public void webDriverOne()
    {
        firefoxHelper.getDriver().get("http://www.amazon.com/");
        assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", firefoxHelper.getDriver().getTitle());
    }
}

