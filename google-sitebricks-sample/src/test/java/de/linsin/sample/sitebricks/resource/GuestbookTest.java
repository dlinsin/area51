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