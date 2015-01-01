package qa.example.framework;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
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
public abstract class JUnitSauceTestBase
{
    @Autowired
    protected FirefoxSauceSpringHelper firefoxHelper;

    @Rule
    public TestName testName = new TestName();

}
