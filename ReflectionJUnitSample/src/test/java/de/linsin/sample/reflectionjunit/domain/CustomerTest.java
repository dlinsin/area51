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
