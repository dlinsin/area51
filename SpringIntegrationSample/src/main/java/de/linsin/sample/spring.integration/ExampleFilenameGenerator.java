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
