package com.github.akizminet.botman.domain.meal;

import com.github.akizminet.botman.domain.telegram.InlineKeyboardButton;
import com.github.akizminet.botman.domain.telegram.InlineKeyboardMarkup;
import java.util.ArrayList;
import java.util.List;

public record MealCalendar(Boolean breakfast, Boolean lunch, Boolean dinner) {

    public final InlineKeyboardMarkup markup() {
        var buttons = new ArrayList<ArrayList<InlineKeyboardButton>>();
        var row1 = new ArrayList<InlineKeyboardButton>();
        row1.add(MealButton.BREAKFAST.getInlineKeyboardButton(breakfast));
        row1.add(MealButton.LUNCH.getInlineKeyboardButton(lunch));
        row1.add(MealButton.DINNER.getInlineKeyboardButton(dinner));
        var row2 = new ArrayList<InlineKeyboardButton>();
        row2.add(new InlineKeyboardButton("Chốt", "CONFIRM"));
        buttons.add(row1);
        buttons.add(row2);
        return new InlineKeyboardMarkup(buttons);
    }

    public final String description() {
        List<String> desc = new ArrayList<>();
        if (breakfast) desc.add(MealButton.BREAKFAST.toString());
        if (lunch) desc.add(MealButton.LUNCH.toString());
        if (dinner) desc.add(MealButton.DINNER.toString());
        return String.join("+", desc);
    }

    public MealCalendar withClickedMeal(MealButton meal) {
        return switch (meal) {
            case BREAKFAST -> new MealCalendar(!breakfast, lunch, dinner);
            case LUNCH -> new MealCalendar(breakfast, !lunch, dinner);
            case DINNER -> new MealCalendar(breakfast, lunch, !dinner);
            default -> this;
        };
    }
}
