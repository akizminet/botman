package com.github.akizminet.botman.domain.telegram;

public record CallbackQuery(
        String id, User from, Message message, String inline_message_id, String chatInstance, String data) {}
