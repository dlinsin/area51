/*
 * Copyright 2009 David Linsin
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package de.linsin.sample.spring.integration;

import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.core.Message;

/**
 * Just trying !!
 *
 * @author David Linsin
 * @version 0.1
 * @since 0.1
 */
public class ExampleFilenameGenerator implements FileNameGenerator {
    public ExampleFilenameGenerator() {
    }

    public String generateFileName(Message<?> argMessage) {
        return "tmp-".concat(argMessage.getHeaders().getTimestamp().toString());
    }
}
