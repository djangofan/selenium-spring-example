package qa.example.scenarios;

import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import qa.example.framework.SauceTestBase;

import java.net.URL;
import java.util.LinkedList;
import static org.junit.Assert.*;

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs using multiple browsers in parallel.
 */
@RunWith(ConcurrentParameterized.class)
public class SampleSauceTest extends SauceTestBase
{
    public SampleSauceTest(String os, String version, String browser) {
        super(os, version, browser);
    }

    @ConcurrentParameterized.Parameters
    public static LinkedList browserScenarios()
    {
        LinkedList browsers = new LinkedList();
        browsers.add(new String[]{Platform.WINDOWS.name(), "39", "chrome"});
        browsers.add(new String[]{Platform.WINDOWS.name(), "33", "firefox"});
        browsers.add(new String[]{Platform.LINUX.name(), "33", "firefox"});
        return browsers;
    }

    @Test
    public void webDriverOne()
    {
        driver.get("http://www.amazon.com/");
        assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
    }

}

