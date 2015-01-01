package qa.example.framework;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.Rule;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A Firefox RemoteWebDriver for SauceLabs.
 */
@Configuration
public class FirefoxSauceSpringHelper implements SauceOnDemandSessionIdProvider
{
    private String sessionId;
    private RemoteWebDriver driver;

    @Autowired
    protected ApplicationContext ac;

    /**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the
     * authentication supplied by environment variables or from an external .sauce-ondemand properties file, use the
     * no-arg {@link com.saucelabs.common.SauceOnDemandAuthentication} constructor.
     */
    @Autowired
    public SauceOnDemandAuthentication authentication;

    /**
     * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
     */
    @Autowired
    public SauceOnDemandTestWatcher resultReportingTestWatcher;

    public FirefoxSauceSpringHelper()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        capabilities.setCapability(CapabilityType.VERSION, "33");
        capabilities.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS.name());
        URL target = null;
        try {
            target = new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub");
        } catch ( MalformedURLException me ) {
            me.printStackTrace();
        }
        this.driver = new RemoteWebDriver(target, capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
    }

    public void quit()
    {
        driver.quit();
    }

    @Override
    public String getSessionId()
    {
        return sessionId.toString();
    }

    public RemoteWebDriver getDriver()
    {
        return this.driver;
    }

}
