package qa.example.tests;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import qa.example.test.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class SpringAppTests {
    @Autowired
    private HelloService helloService;

    @Test
    public void testSayHello() {
        assertEquals("Hello world!", helloService.sayHello());
    }
}
