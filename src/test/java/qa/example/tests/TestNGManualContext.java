package qa.example.tests;
import qa.example.UserStory;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import static org.testng.Assert.*;

/* Here we run one of the same tests we run in ContextTests, but we separate it out here to demonstrate
 *  we can do it with a POJU (Plain Old JUnit :) test runner.
 */
public class TestNGManualContext{

    ApplicationContext ac;

    @BeforeClass
    public void setUp()
    {
        ac = new FileSystemXmlApplicationContext("file:src/main/resources/spring-config.xml");
    }

    @Test
    public void testUserCorrectFromPlainOldJUnitTest() {

        UserStory story = (UserStory) ac.getBean("userStory");
        // Spring is working fine using this app context
        assertEquals(story.getUser().getRole(), "SuperGenius User");

        // In this case our User is not wired up
        UserStory story2 = new UserStory();
        assertNull(story2.getUser());
    }

    @Test
    public void testPrewiredUserCorrect() {
        UserStory story = (UserStory) ac.getBean("userStory");
        assertEquals(story.getUser().getRole(), "SuperGenius User");
    }
}
