package com.github.akizminet.botman.domain.telegram;

public record InaccessibleMessage(Chat chat, int messageId, Long date) {}
