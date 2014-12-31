package qa.example.tests;

import qa.example.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class SpringAppTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private HelloService helloService;

    @Test
    public void testSayHello() {
        assertEquals("Hello world!", helloService.sayHello());
    }
}
