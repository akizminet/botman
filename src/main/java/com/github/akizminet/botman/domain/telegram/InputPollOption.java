package com.github.akizminet.botman.domain.telegram;

import java.util.List;

public record InputPollOption(String text, String textParseMode, List<MessageEntity> textEntities) {
    public InputPollOption(String text) {
        this(text, "MarkdownV2", null);
    }
}
