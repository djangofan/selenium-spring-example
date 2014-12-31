package qa.example.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;
import qa.example.test.BDDUserStory;
import qa.example.test.User;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class ContextTests {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected ApplicationContext ac;

    // Using ApplicationContext
    // The getBean method is the workhorse of the Spring ApplicationContext.
    // The user field in the BDDUserStory object was set up (dependency injected)
    // by Spring:
    @Test
    public void testUserNotNull() {
        BDDUserStory story = (BDDUserStory) ac.getBean("userStory");
        assertNotNull(story.getUser());
    }

    // Not using ApplicationContext
    // Regular non-Spring instantiation
    // Instantiating our own BDDUserStory, you can see that the user
    // is null.
    @Test
    public void testUserStoryNotFromContext() {
        BDDUserStory story = new BDDUserStory();
        assertNull(story.getUser());
    }

    // Not using ApplicationContext
    // Regular non-Spring instantiation
    // The default role if you just call the constructor
    // is "User".
    @Test
    public void testNonSpringUserDefaultRole()
    {
        User u = new User();
        assertEquals(u.getRole(), "User");
    }

    // Using ApplicationContext
    // Since Spring instantiated this according to the property
    // from spring-config.xml, it overrode the default "user"
    // role with "SuperGenius User".  (This presumably
    // is Wile E. Coyote.)
    @Test
    public void testUserRoleIsAsExpected() {
        BDDUserStory story = (BDDUserStory) ac.getBean("userStory");
        assertEquals(story.getUser().getRole(), "SuperGenius User");
    }
}
