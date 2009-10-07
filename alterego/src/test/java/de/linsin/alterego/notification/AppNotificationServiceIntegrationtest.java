package de.linsin.alterego.notification;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing {@link AppNotificationService}
 *
 * @author David Linsin - linsin@synyx.de
 */
public class AppNotificationServiceIntegrationtest {
    private AppNotificationService classUnderTest;
    private String credentials = System.getProperty("credentials");

    @Before
    public void setUp() {
        classUnderTest = new AppNotificationService(credentials);
    }

    @Test
    public void test_send_notification() {
        assertTrue(classUnderTest.notify("test", "test message from junit"));
    }

    @Test
    public void test_send_long() {
        assertTrue(classUnderTest.notify("test2", longString()));
    }

    private String longString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i <= 50) {
            builder.append("asdasdasdasdasdasdasdasdasdasda");
            i++;
        }
        return builder.toString();
    }

    @Test
    public void test_send_empty() {
        assertTrue(classUnderTest.notify("", ""));
    }

    @Test(expected = AppNotificationException.class)
    public void test_send_null() {
        classUnderTest.notify(null, null);
    }
}