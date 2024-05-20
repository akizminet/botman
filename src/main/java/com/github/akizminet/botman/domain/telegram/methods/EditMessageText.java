package com.github.akizminet.botman.domain.telegram.methods;

import com.github.akizminet.botman.domain.telegram.ReplyMarkup;
import java.util.Optional;

public record EditMessageText(
        Optional<Integer> chatId,
        Optional<Integer> messageId,
        Optional<String> inlineMessageId,
        String text,
        Optional<ReplyMarkup> replyMarkup)
        implements Method {
    public EditMessageText(int chatId, int messageId, String text, ReplyMarkup replyMarkup) {
        this(Optional.of(chatId), Optional.of(messageId), Optional.empty(), text, Optional.of(replyMarkup));
    }

    public EditMessageText(String inlineMessageId, String text, ReplyMarkup replyMarkup) {
        this(Optional.empty(), Optional.empty(), Optional.of(inlineMessageId), text, Optional.of(replyMarkup));
    }
}
