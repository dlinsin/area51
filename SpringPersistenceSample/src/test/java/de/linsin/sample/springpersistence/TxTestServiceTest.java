package de.linsin.sample.springpersistence;

import de.linsin.sample.springpersistence.service.TxTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * TODO document
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:jdbc-config.xml", "classpath:bean-config.xml", "classpath:tx-config.xml"})
public class TxTestServiceTest {
    TxTestService service;

    @Autowired
    public void setService(TxTestService argService) {
        service = argService;
    }

    @Test
    public void testGetUserCount() {
        service.addDefaultUserToDB();
        assertEquals(1, service.getCount());
    }
}
