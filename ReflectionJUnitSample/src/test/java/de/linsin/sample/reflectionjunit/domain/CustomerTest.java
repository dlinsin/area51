package de.linsin.sample.reflectionjunit.domain;

import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.joda.time.DateMidnight;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;


/**
 * Testing Customer's business logic using easymock
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
@RunWith(org.junit.internal.runners.JUnit4ClassRunner.class)
public class CustomerTest {

    @Test
    public void test_Add_Contract() {
        Customer classUnderTest = new Customer(1L, new DateMidnight(1982, 4, 27));
        assertTrue(classUnderTest.getContracts().isEmpty());

        Contract contractMock = createMock(Contract.class);
        contractMock.setOwner(classUnderTest);
        replay(contractMock);
        classUnderTest.addContract(contractMock);
        assertFalse(classUnderTest.getContracts().isEmpty());
        verify(contractMock);
    }
}
