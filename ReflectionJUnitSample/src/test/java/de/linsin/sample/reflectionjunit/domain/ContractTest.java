package de.linsin.sample.reflectionjunit.domain;

import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.expect;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import org.joda.time.DateMidnight;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Testing Contract's business logic using easymock & reflection
 *
 * @author David Linsin
 * @version 0.0.1
 * @since 0.0.1
 */
@RunWith(org.junit.internal.runners.JUnit4ClassRunner.class)
public class ContractTest {

    @Test
    public void test_Set_Owner() {
        Contract classUnderTest = new Contract(1L);
        Customer customerMock = createMock(Customer.class);
        assertEquals(BigDecimal.ZERO, classUnderTest.getQuote());

        expect(customerMock.getBirthday()).andReturn(new DateMidnight(1982, 4, 27));
        replay(customerMock);
        classUnderTest.setOwner(customerMock);
        assertTrue(classUnderTest.getQuote().compareTo(BigDecimal.ZERO) >= 1);
        verify(customerMock);
    }

    @Test
    public void test_Calculate_Quote() throws Exception {
        Contract classUnderTest = new Contract(1L);
        Method calcMethod = getMethodOfClass(Contract.class, "calculateQuote");
        BigDecimal quote = (BigDecimal) calcMethod.invoke(classUnderTest, new DateMidnight(1982, 4, 27));
        assertEquals(new BigDecimal("7.79"), quote.setScale(2, RoundingMode.FLOOR));
    }

    private Method getMethodOfClass(Class<?> argClass, String argMethodName) {
        Method[] methods = argClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(argMethodName)) {
                method.setAccessible(true);
                return method;
            }
        }
        throw new NoSuchMethodError("couldn't find " + argMethodName + " on class " + argClass);
    }
}
