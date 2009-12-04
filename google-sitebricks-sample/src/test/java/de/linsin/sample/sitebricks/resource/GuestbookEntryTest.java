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

import de.linsin.sample.sitebricks.dao.EntryDao;
import de.linsin.sample.sitebricks.domain.Entry;
import static org.easymock.EasyMock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing {@link GuestbookEntry}
 *
 * @author David Linsin - linsin@synyx.de
 */
public class GuestbookEntryTest {
    private GuestbookEntry classUnderTest;
    private EntryDao daoMock;

    @Before
    public void setUp() {
        daoMock = createMock(EntryDao.class);
        classUnderTest = new GuestbookEntry();
        classUnderTest.setEntryDao(daoMock);
    }

    @After
    public void tearDown() {
        reset(daoMock);
    }

    @Test
    public void test_load() {
        expect(daoMock.read(1)).andReturn(new Entry());
        replay(daoMock);
        classUnderTest.load("1");
        verify(daoMock);
    }

    @Test
    public void test_delete() {
        Entry entry = new Entry();
        expect(daoMock.read(1)).andReturn(entry);
        daoMock.delete(entry);
        replay(daoMock);
        classUnderTest.delete("1");
        verify(daoMock);

    }
}