package org.crcaguilerapo;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MaskingRewrite extends MessageConverter {
    private static final List<String> WORDS_TO_MASK = Arrays.asList("password:", "username:");
    private static final String MASK = "$1****";
    private static final Pattern PATTERN = Pattern
            .compile("($WORDS)(\\w+)"
                    .replace("$WORDS",  String.join("|", WORDS_TO_MASK)));

    @Override
    public String convert(ILoggingEvent event) {
        String message = event.getFormattedMessage();
        return PATTERN.matcher(message).replaceAll(MASK);
    }
}