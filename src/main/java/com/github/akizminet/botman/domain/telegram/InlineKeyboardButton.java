package com.github.akizminet.botman.domain.telegram;

public record InlineKeyboardButton(
    String text,
    String callbackData
){}