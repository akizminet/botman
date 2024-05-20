package com.github.akizminet.botman.domain.meal;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Optional;

import com.github.akizminet.botman.domain.telegram.InlineKeyboardButton;
import com.github.akizminet.botman.domain.telegram.InlineKeyboardMarkup;

public record Meal(
        MealId id, OffsetDateTime date, MealProperties properties) {

    public static final String QUESTION = "Ngày mai %s bạn ăn những buổi nào?";
    public static final String CONFIRM = "Ngày %s bạn ăn %s";

    public final Optional<Meal> choose(MealButton option) {
        return switch (properties) {
            case MealProperties.Poll p ->
                Optional.of(new Meal(id, date, new MealProperties.Poll(p.options().withClickedMeal(option))));
            case MealProperties.Confirmed _c -> Optional.empty();
        };
    }

    public final Optional<Meal> edit() {
        return switch (properties) {
            case MealProperties.Poll p -> Optional.empty();
            case MealProperties.Confirmed c -> Optional.of(new Meal(id, date, new MealProperties.Poll(c.result())));
        };
    }

    public final Optional<Meal> confirm() {
        return switch (properties) {
            case MealProperties.Poll p -> Optional.of(new Meal(id, date, new MealProperties.Confirmed(p.options())));
            case MealProperties.Confirmed c -> Optional.empty();
        };
    }

    public final String message() {
        return switch (properties) {
            case MealProperties.Poll p -> String.format(QUESTION, date.toLocalDate());
            case MealProperties.Confirmed c -> String.format(CONFIRM, date.toLocalDate(),c.result().description());
        };
    }

    public final InlineKeyboardMarkup markup() {
        if (properties instanceof MealProperties.Poll p) {
            return p.options().markup();
        } else {
            var buttons = new ArrayList<ArrayList<InlineKeyboardButton>>();
            var row = new ArrayList<InlineKeyboardButton>();
            row.add(new InlineKeyboardButton("Sửa", MealButton.EDIT.name()));
            buttons.add(row);
            return new InlineKeyboardMarkup(buttons);
        }
    }

}
