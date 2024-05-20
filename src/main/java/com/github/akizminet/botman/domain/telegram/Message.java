package com.github.akizminet.botman.domain.telegram;

import java.util.List;
import java.util.Optional;

public record Message(
        int messageId,
        int messageThreadId,
        User from,
        Optional<Chat> senderChat,
        Long date,
        Chat chat,
        String text,
        List<MessageEntity> entities
){}
