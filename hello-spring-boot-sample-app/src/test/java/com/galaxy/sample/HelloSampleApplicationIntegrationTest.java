package com.galaxy.sample;

import com.galaxy.autoconfigure.Hello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by wangpeng
 * Date: 2018/9/4
 * Time: 18:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloSampleApplication.class)
public class HelloSampleApplicationIntegrationTest {

    @Autowired
    private Hello hello;

    @Test
    public void givenMorningTime_ifMorningMessage_thenSuccess() {
        String expected = "Hello Starter, Good Morning";
        String actual = hello.greet(LocalDateTime.of(2018, 3, 1, 6, 0));
        assertEquals(expected, actual);
    }

    @Test
    public void givenAfternoonTime_ifAfternoonMessage_thenSuccess() {
        String expected = "Hello Starter, Woha Afternoon";
        String actual = hello.greet(LocalDateTime.of(2018, 3, 1, 13, 0));
        assertEquals(expected, actual);
    }

    @Test
    public void givenEveningTime_ifEveningMessage_thenSuccess() {
        String expected = "Hello Starter, Good Evening";
        String actual = hello.greet(LocalDateTime.of(2018, 3, 1, 19, 0));
        assertEquals(expected, actual);
    }

    @Test
    public void givenNightTime_ifNightMessage_thenSuccess() {
        String expected = "Hello Starter, Good Night";
        String actual = hello.greet(LocalDateTime.of(2018, 3, 1, 21, 0));
        assertEquals(expected, actual);
    }
}
