/*
 * Copyright 2009 David Linsin
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.linsin.sample.sitebricks.resource;

import java.util.ArrayList;

import de.linsin.sample.sitebricks.dao.EntryDao;
import de.linsin.sample.sitebricks.domain.Entry;
import static org.easymock.EasyMock.*;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing {@link Guestbook}
 *
 * @author David Linsin - linsin@synyx.de
 */
public class GuestbookTest {
    private Guestbook classUnderTest;
    private EntryDao daoMock;

    @Before
    public void setUp() {
        daoMock = createMock(EntryDao.class);
        classUnderTest = new Guestbook();
        classUnderTest.setEntryDao(daoMock);
    }

    @After
    public void tearDown() {
        reset(daoMock);
    }

    @Test
    public void test_load() {
        expect(daoMock.readAll()).andReturn(new ArrayList<Entry>());
        replay(daoMock);
        classUnderTest.load();
        verify(daoMock);
    }

    @Test
    public void test_save() {
        expect(daoMock.save(isA(Entry.class))).andReturn(1);
        replay(daoMock);
        assertEquals("/sample/guestbook/1", classUnderTest.save());
        verify(daoMock);
    }
}