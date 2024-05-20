package com.github.akizminet.botman.services;

import java.time.OffsetDateTime;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.github.akizminet.botman.domain.meal.Meal;
import com.github.akizminet.botman.domain.meal.MealButton;
import com.github.akizminet.botman.domain.meal.MealCalendar;
import com.github.akizminet.botman.domain.meal.MealId;
import com.github.akizminet.botman.domain.meal.MealProperties;
import com.github.akizminet.botman.domain.telegram.methods.EditMessageReplyMarkup;
import com.github.akizminet.botman.domain.telegram.methods.EditMessageText;
import com.github.akizminet.botman.domain.telegram.methods.SendMessage;
import com.github.akizminet.botman.repositories.MealRepo;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MealService {

    @Inject
    @RestClient
    TelegramClient telegramClient;

    @Inject
    MealRepo mealRepo;

    public void createMealCalendar(int chatId) {
        final var defaultMealCalendar = new MealCalendar(false, false, false);
        final var currentDate = OffsetDateTime.now().toLocalDate();
        final var command = new SendMessage(chatId, Meal.QUESTION, defaultMealCalendar.markup());
        final var response = telegramClient.sendMessage(command);
        Log.infof("New message: %s", response);
        mealRepo.save(new Meal(
                new MealId(chatId, response.result().messageId()),
                OffsetDateTime.now(),
                new MealProperties.Poll(defaultMealCalendar)));
    }

    private void publish(MealButton button, Meal meal) {
        switch (button) {
            case BREAKFAST -> telegramClient.editMessageReplyMarkup(
                    new EditMessageReplyMarkup(meal.id().chatId(), meal.id().messageId(), meal.markup()));
            case LUNCH -> telegramClient.editMessageReplyMarkup(
                    new EditMessageReplyMarkup(meal.id().chatId(), meal.id().messageId(), meal.markup()));
            case DINNER -> telegramClient.editMessageReplyMarkup(
                    new EditMessageReplyMarkup(meal.id().chatId(), meal.id().messageId(), meal.markup()));
            case CONFIRM -> telegramClient.editMessageText(
                    new EditMessageText(meal.id().chatId(), meal.id().messageId(), meal.message(), meal.markup()));
            case EDIT -> telegramClient.editMessageText(
                    new EditMessageText(meal.id().chatId(), meal.id().messageId(), meal.message(), meal.markup()));
        }
        ;
    }

    public void doUpdate(MealId id, MealButton button) {
        final var mealOpt = mealRepo.findById(id);
        if (mealOpt.isPresent()) {
            final var meal = mealOpt.get();
            final var newMeal = switch (button) {
                case MealButton.BREAKFAST -> meal.choose(button);
                case MealButton.LUNCH -> meal.choose(button);
                case MealButton.DINNER -> meal.choose(button);
                case MealButton.CONFIRM -> meal.confirm();
                case MealButton.EDIT -> meal.edit();
            };
            if (newMeal.isPresent()) {
                mealRepo.save(newMeal.get());
                publish(button, newMeal.get());
            }
        }
    }

}
