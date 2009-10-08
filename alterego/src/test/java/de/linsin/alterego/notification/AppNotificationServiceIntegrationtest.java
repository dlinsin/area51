/*
 * Copyright (C) 2009  <David Linsin>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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