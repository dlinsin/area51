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

import java.io.IOException;

import static de.linsin.alterego.notification.AppNotificationService.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import static org.easymock.classextension.EasyMock.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing {@link AppNotificationService}
 *
 * @author David Linsin - linsin@synyx.de
 */
public class AppNotificationServiceTest {
    private AppNotificationService classUnderTest;
    private HttpClient mockHttpClient;
    private static final String MYCREDS = "mycreds";

    @Before
    public void setUp() {
        mockHttpClient = createMock(HttpClient.class);
        classUnderTest = new AppNotificationService(MYCREDS) {
            @Override
            HttpClient setUp() {
                return mockHttpClient;
            }
        };
    }

    @After
    public void tearDown() {
        reset(mockHttpClient);
    }

    @Test
    public void test_setup_postmethod() {
        String msg = "mymässage";
        String title = "mytitle";
        PostMethod method = classUnderTest.setUp(title, msg);
        assertEquals(MYCREDS, method.getParameter(USER_CREDENTIALS).getValue());
        assertEquals(msg, method.getParameter(NOTIFICATION_LONG_MESSAGE).getValue());
        assertEquals(title, method.getParameter(NOTIFICATION_TITLE).getValue());
        assertEquals("2", method.getParameter(MESSAGE_LEVEL).getValue());
    }

    @Test
    public void test_notify() throws IOException {
        int ok = 200;
        expect(mockHttpClient.executeMethod((HttpMethod) anyObject())).andReturn(ok);
        replay(mockHttpClient);
        assertTrue(classUnderTest.notify("", ""));
        verify(mockHttpClient);
    }

    @Test
    public void test_notify_problem() throws IOException {
        int nok = 404;
        expect(mockHttpClient.executeMethod((HttpMethod) anyObject())).andReturn(nok);
        replay(mockHttpClient);
        assertFalse(classUnderTest.notify("", ""));        
        verify(mockHttpClient);
    }

    @Test(expected = AppNotificationException.class)
    public void test_notify_infrastructure_problem() throws Exception {
        expect(mockHttpClient.executeMethod((HttpMethod) anyObject())).andThrow(new IOException());
        replay(mockHttpClient);
        assertFalse(classUnderTest.notify("", ""));
    }
}