package com.github.akizminet.botman.controllers;

import com.github.akizminet.botman.domain.meal.MealButton;
import com.github.akizminet.botman.domain.meal.MealId;
import com.github.akizminet.botman.domain.telegram.Update;
import com.github.akizminet.botman.domain.telegram.methods.AnswerCallbackQuery;
import com.github.akizminet.botman.domain.telegram.methods.Method;
import com.github.akizminet.botman.domain.telegram.methods.SendMessage;
import com.github.akizminet.botman.services.MealService;
import com.github.akizminet.botman.services.TelegramClient;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.Optional;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
public class BotResource {

    @Inject
    MealService mealService;

    @Inject
    @RestClient
    TelegramClient telegramClient;

    @POST
    @Path("update")
    @RunOnVirtualThread
    public Method listen(Update update) {
        Log.info(update);
        if (update.message().isPresent()) {
            return new SendMessage(
                    update.message().get().chat().id(),
                    String.format(
                            "Hello [%s](tg://user?id=%d)",
                            update.message().get().from().fullName(),
                            update.message().get().from().id()));
        }
        if (update.callbackQuery().isPresent()) {
            try {
                final int chatId = update.callbackQuery().get().message().chat().id();
                final int messageId = update.callbackQuery().get().message().messageId();
                mealService.doUpdate(
                        new MealId(chatId, messageId),
                        MealButton.valueOf(update.callbackQuery().get().data()));
                return new AnswerCallbackQuery(
                        update.callbackQuery().get().id(),
                        update.callbackQuery().get().data());

            } catch (Exception e) {
                return new AnswerCallbackQuery(update.callbackQuery().get().id(), Optional.empty());
            }
        }
        if (update.pollAnswer().isPresent()) {
            if (update.pollAnswer().get().voterChat().isPresent()) {
                return new SendMessage(
                        update.pollAnswer().get().voterChat().get().id(), "Đã ghi nhận vote của bạn");
            }
            return null;
        }
        return null;
    }

    @GET
    @Path("meal")
    public void meal() {
        mealService.createMealCalendar(827879582);
    }
}
