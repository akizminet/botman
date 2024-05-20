package com.github.akizminet.botman.domain.telegram.methods;

import java.util.Optional;

/** @author phamnv */
public record AnswerCallbackQuery(String callbackQueryId, Optional<String> text) implements Method {
    public AnswerCallbackQuery(String callbackQueryId, String text) {
        this(callbackQueryId, Optional.of(text));
    }
}
