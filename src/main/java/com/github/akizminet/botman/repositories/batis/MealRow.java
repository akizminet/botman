package com.github.akizminet.botman.repositories.batis;

import java.time.OffsetDateTime;

import com.github.akizminet.botman.domain.meal.Meal;
import com.github.akizminet.botman.domain.meal.MealCalendar;
import com.github.akizminet.botman.domain.meal.MealId;
import com.github.akizminet.botman.domain.meal.MealProperties;

public record MealRow(
        int chatId,
        int messageId,
        OffsetDateTime date,
        Boolean breakfast,
        Boolean lunch,
        Boolean dinner,
        String type) {
    public static final MealRow of(Meal mealMessage) {
        MealCalendar calendar = null;
        String type = null;
        switch (mealMessage.properties()) {
            case MealProperties.Poll p -> {
                calendar = p.options();
                type = "Poll";
            }
            case MealProperties.Confirmed c -> {
                calendar = c.result();
                type = "Confirmed";
            }
        }
        ;
        return new MealRow(mealMessage.id().chatId(), mealMessage.id().messageId(), mealMessage.date(),
                calendar.breakfast(),
                calendar.lunch(), calendar.dinner(), type);
    }

    public final Meal toMeal() {
        final var prop = switch (type) {
            case "Poll" -> new MealProperties.Poll(new MealCalendar(breakfast, lunch, dinner));
            case "Confirmed" -> new MealProperties.Confirmed(new MealCalendar(breakfast, lunch, dinner));
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };
        return new Meal(new MealId(chatId, messageId), date, prop);
    }
}
