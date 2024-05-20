package com.github.akizminet.botman.domain.telegram.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.akizminet.botman.domain.telegram.MessageEntity;
import com.github.akizminet.botman.domain.telegram.ReplyMarkup;

public record SendMessage(
                int chatId,
                Optional<Integer> messageThreadId,
                String text,
                Optional<String> parseMode,
                List<MessageEntity> entities,
                Optional<ReplyMarkup> replyMarkup) implements Method {
        public SendMessage(int chatId, String text) {
                this(chatId, Optional.empty(), text, Optional.of("MarkdownV2"), new ArrayList<>(), Optional.empty());
        }

        public SendMessage(int chatId, String text, ReplyMarkup replyMarkup) {
                this(chatId, Optional.empty(), text, Optional.of("MarkdownV2"), new ArrayList<>(),
                                Optional.of(replyMarkup));
        }
}
