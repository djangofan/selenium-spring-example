package qa.example.test;

/**
 * A test base class for SauceLabs tests.
 */
public class SauceTestBase implements SessionIdProvider {

    @Rule
    public ResultReportingTestWatcher resultReportingTestWatcher = new ResultReportingTestWatcher(this, sauceLabAccount, sauceLabAccessKey);

    @Rule
    public TestName testName = new TestName();

    private SessionId sessionId;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "5");
        capabilities.setCapability("platform", Platform.XP);
        capabilities.setCapability("name", "xxTest : "+testName.getMethodName());

        this.driver = new RemoteWebDriver(
                new URL("http://" + sauceLabAccount + ":" + sauceLabAccessKey + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        sessionId=((RemoteWebDriver)driver).getSessionId();
    }


    @Override
    public String getSessionId() {
        return sessionId.toString();
    }

}
