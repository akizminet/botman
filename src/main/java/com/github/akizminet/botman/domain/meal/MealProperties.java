package com.github.akizminet.botman.domain.meal;

import com.github.akizminet.botman.domain.telegram.InlineKeyboardButton;
import com.github.akizminet.botman.domain.telegram.InlineKeyboardMarkup;
import java.util.ArrayList;

public sealed interface MealProperties {
    public InlineKeyboardMarkup markup();

    public static record Poll(MealCalendar options) implements MealProperties {

        @Override
        public final InlineKeyboardMarkup markup() {
            return options.markup();
        }
    }

    public static record Confirmed(MealCalendar result) implements MealProperties {

        @Override
        public final InlineKeyboardMarkup markup() {
            var buttons = new ArrayList<ArrayList<InlineKeyboardButton>>();
            var row = new ArrayList<InlineKeyboardButton>();
            row.add(new InlineKeyboardButton("Sửa", "Edit"));
            buttons.add(row);
            return new InlineKeyboardMarkup(buttons);
        }
    }
}
