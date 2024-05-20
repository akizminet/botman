package com.github.akizminet.botman.domain.telegram.methods;

import java.util.Optional;

import com.github.akizminet.botman.domain.telegram.ReplyMarkup;

public record EditMessageReplyMarkup(
                Optional<Integer> chatId,
                Optional<Integer> messageId,
                Optional<String> inlineMessageId,
                Optional<ReplyMarkup> replyMarkup
                ) implements Method {
        public EditMessageReplyMarkup(int chatId, int messageId, ReplyMarkup replyMarkup) {
                this(Optional.of(chatId), Optional.of(messageId), Optional.empty(), Optional.of(replyMarkup));
        }
        public EditMessageReplyMarkup(String inlineMessageId, ReplyMarkup replyMarkup) {
                this(Optional.empty(), Optional.empty(), Optional.of(inlineMessageId), Optional.of(replyMarkup));
        }
}
