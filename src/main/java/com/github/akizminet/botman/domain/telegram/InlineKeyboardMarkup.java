package com.github.akizminet.botman.domain.telegram;

import java.util.ArrayList;

public record InlineKeyboardMarkup(ArrayList<ArrayList<InlineKeyboardButton>> inlineKeyboard) implements ReplyMarkup {}
