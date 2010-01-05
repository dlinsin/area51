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

package org.synyx.sample;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.junit.Test;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

import static org.junit.Assert.*;

/**
 * This test demonstrates that Spring's MessagesAwareResourceBundle swallows an IllegalArgumentException
 *
 * @author David Linsin - linsin@synyx.de
 */
public class ResourceBundleMessageSourceTest {

    @Test
    public void test_util_resource_bundle_with_ok() {
        ResourceBundle bundle = ResourceBundle.getBundle("ok_messages");
        String msg = bundle.getString("test.1");
        assertEquals("david", msg);
    }

    @Test
    public void test_util_resource_bundle_with_broken() {
        try {
            ResourceBundle.getBundle("broken_messages");
            fail("Should throw Exception due to wrong format of file");
        } catch (MissingResourceException e) {
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
    }

    @Test
    public void test_resource_bundle_msg_source_ok() throws Exception {
        ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
        bundle.setBasename("ok_messages");
        String msg = bundle.getMessage("test.1", new Object[0], Locale.getDefault());
        assertEquals("david", msg);
    }

    @Test
    public void test_resource_bundle_msg_source_broken() throws Exception {
        try {
            ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
            bundle.setBasename("broken_messages");
            bundle.getMessage("test.1", new Object[0], Locale.getDefault());
            fail("Should throw Exception due to wrong format of file");
        } catch (NoSuchMessageException e) {
            // SHOULD have a cause to indicate why the message is missing
            assertNotNull(e.getCause());
        }
    }
}
