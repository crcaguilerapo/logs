package org.crcaguilerapo;

import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.ParameterizedMessage;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Plugin(name = "MaskingRewritePolicy", category = Core.CATEGORY_NAME, elementType = "rewritePolicy", printObject = true)
public class MaskingRewritePolicy implements RewritePolicy {
    private static final List<String> WORDS_TO_MASK = Arrays.asList("password:", "username:");
    private static final String MASK = "$1****";
    private static final Pattern PATTERN = Pattern
            .compile("($WORDS)(\\w+)"
                    .replace("$WORDS",  String.join("|", WORDS_TO_MASK)));

    @Override
    public LogEvent rewrite(final LogEvent event) {
        Message message = event.getMessage();

        String formattedMessage = message.getFormattedMessage();
        formattedMessage = PATTERN.matcher(formattedMessage).replaceAll(MASK);
        message = new ParameterizedMessage(formattedMessage, new Object[]{});

        return new Log4jLogEvent.Builder(event).setMessage(message).build();
    }

    @PluginFactory
    public static MaskingRewritePolicy createPolicy() {
        return new MaskingRewritePolicy();
    }
}