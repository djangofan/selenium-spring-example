package qa.example.framework;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Test base class for SauceLabs tests.
 * <p/>
 * The test is annotated with the {@link com.saucelabs.junit.ConcurrentParameterized} test runner...
 * <p/>
 * The test also includes the {@link SauceOnDemandTestWatcher}...
 */
public abstract class SauceTestBase implements SauceOnDemandSessionIdProvider
{
    public String sauceLabAccount = "austenjt";
    public String sauceLabAccessKey = "bde85696-2a40-486b-98de-38ff7e13d6fc";
    protected WebDriver driver;
    protected String sessionId;
    public String browser;
    public String os;
    public String version;

    /**
     * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
     */
    @Rule
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication();

    /**
     * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
     */
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    @Rule
    public TestName testName = new TestName();

    public SauceTestBase(String os, String version, String browser)
    {
        this.os = os;
        this.version = version;
        this.browser = browser;
    }

    @Before
    public void setUp()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        URL target = null;
        try {
            target = new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub");
        } catch ( MalformedURLException me ) {
            me.printStackTrace();
        }
        this.driver = new RemoteWebDriver(target, capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
    }

    @After
    public void cleanUp()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @Override
    public String getSessionId()
    {
        return sessionId.toString();
    }

}
