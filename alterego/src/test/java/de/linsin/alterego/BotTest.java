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

package de.linsin.alterego;

import de.linsin.alterego.notification.NotificationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.easymock.classextension.EasyMock.*;

/**
 * Testing {@link Bot}
 *
 * @author David Linsin - linsin@synyx.de
 */
public class BotTest {
    private Bot classUnderTest;
    private NotificationService mockNotificationService;

    @Before
    public void setUp() {
        mockNotificationService = createMock(NotificationService.class);
        classUnderTest = new Bot("", "", "");
        classUnderTest.addNotificationService(mockNotificationService);
    }

    @After
    public void tearDown() {
        reset(mockNotificationService);
    }

    @Test
    public void test_on_message_over_batch_size() {
        expect(mockNotificationService.notify((String)anyObject(), (String)anyObject())).andReturn(true);
        replay(mockNotificationService);
        for (int i = 0; i <= Bot.MESSAGE_BATCH_SIZE; i++) {
            classUnderTest.onMessage("", "", "", "", "test");
        }
        verify(mockNotificationService);
    }

    @Test
    public void test_on_message_timer_exceeded() {

    }

    @Test
    public void test_on_message_silent() {
    }

    @Test
    public void test_on_private_msg() {
    }

    @Test
    public void test_on_private_msg_silent() {
    }

    @Test
    public void test_on_join_alterego() {
    }

    @Test
    public void test_on_join_not_alterego() {
    }

    @Test
    public void test_on_part_alterego() {
    }

    @Test
    public void test_on_part_not_altergo() {
    }

    @Test
    public void test_on_quit_alterego() {
    }

    @Test
    public void test_on_quit_not_altergo() {
    }

    @Test
    public void test_on_userlist_alterego_present() {
    }

    @Test
    public void test_on_userlist_altergo_not_present() {
    }
}