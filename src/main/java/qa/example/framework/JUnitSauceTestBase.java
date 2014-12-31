package qa.example.framework;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Test base class for SauceLabs tests.
 * <p/>
 * The test is annotated with the {@link com.saucelabs.junit.ConcurrentParameterized} test runner...
 * <p/>
 * The test also includes the {@link SauceOnDemandTestWatcher}...
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public abstract class JUnitSauceTestBase implements SauceOnDemandSessionIdProvider
{
    protected WebDriver driver;
    protected String sessionId;
    public String browser;
    public String os;
    public String version;

    @Autowired
    protected ApplicationContext ac;

    /**
     * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the
     * authentication supplied by environment variables or from an external .sauce-ondemand properties file, use the
     * no-arg {@link SauceOnDemandAuthentication} constructor.
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

    public JUnitSauceTestBase(String os, String version, String browser)
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
