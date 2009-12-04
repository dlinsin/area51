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
import java.util.Arrays;
import java.util.Date;

import de.linsin.sample.sitebricks.dao.EntryDao;
import de.linsin.sample.sitebricks.domain.Entry;
import static org.easymock.EasyMock.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing {@link GuestbookNavigation}
 *
 * @author David Linsin - linsin@synyx.de
 */
public class GuestbookNavigationTest {
    private GuestbookNavigation classUnderTest;
    private EntryDao daoMock;

    @Before
    public void setUp() {
        daoMock = createMock(EntryDao.class);
        classUnderTest = new GuestbookNavigation();
        classUnderTest.setEntryDao(daoMock);
    }

    @After
    public void tearDown() {
        reset(daoMock);
    }

    @Test
    public void test_next() {
        ArrayList<Entry> entries = new ArrayList<Entry>(Arrays.asList(new Entry(1, new Date(), "david", "mytext"), new Entry(2, new Date(), "lisa", "hertext")));
        classUnderTest.setCurrent(entries.get(0));

        expect(daoMock.readAll()).andReturn(entries);
        replay(daoMock);
        assertEquals(entries.get(1), classUnderTest.getNext());
        verify(daoMock);
    }

    @Test
    public void test_no_next() {
        expect(daoMock.readAll()).andReturn(new ArrayList<Entry>());
        replay(daoMock);
        assertNull(classUnderTest.getNext());
        verify(daoMock);
    }

    @Test
    public void test_previous() {
        ArrayList<Entry> entries = new ArrayList<Entry>(Arrays.asList(new Entry(1, new Date(), "david", "mytext"), new Entry(2, new Date(), "lisa", "hertext")));
        classUnderTest.setCurrent(entries.get(1));

        expect(daoMock.readAll()).andReturn(entries);
        replay(daoMock);
        assertEquals(entries.get(0), classUnderTest.getPrevious());
        verify(daoMock);
    }

    @Test
    public void test_no_previous() {
        expect(daoMock.readAll()).andReturn(new ArrayList<Entry>());
        replay(daoMock);
        assertNull(classUnderTest.getPrevious());
        verify(daoMock);
    }

    @Test
    public void test_no_previous_only_one() {
        ArrayList<Entry> entries = new ArrayList<Entry>(Arrays.asList(new Entry(1, new Date(), "david", "mytext")));
        classUnderTest.setCurrent(entries.get(0));

        expect(daoMock.readAll()).andReturn(entries);
        replay(daoMock);
        assertNull(classUnderTest.getPrevious());
        verify(daoMock);
    }
}