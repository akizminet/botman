package com.github.akizminet.botman.domain.telegram;

public record MessageEntity(
        String type, int offset, int length, String url, User user, String language, String customEmojiId) {}
