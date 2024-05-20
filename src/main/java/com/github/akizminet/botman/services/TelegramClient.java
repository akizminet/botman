package com.github.akizminet.botman.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.github.akizminet.botman.domain.telegram.Message;
import com.github.akizminet.botman.domain.telegram.Response;
import com.github.akizminet.botman.domain.telegram.methods.EditMessageReplyMarkup;
import com.github.akizminet.botman.domain.telegram.methods.EditMessageText;
import com.github.akizminet.botman.domain.telegram.methods.SendMessage;
import com.github.akizminet.botman.domain.telegram.methods.SendPoll;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "telegram-api")
public interface TelegramClient {

    @POST
    @Path("sendMessage")
    public Response<Message> sendMessage(SendMessage command);

    @POST
    @Path("sendPoll")
    public Response<Message> sendPoll(SendPoll command);

    @POST
    @Path("answerCallbackQuery")
    public Response<Message> answerCallbackQuery(SendPoll command);

    @POST
    @Path("editMessageReplyMarkup")
    public Response<Message> editMessageReplyMarkup(EditMessageReplyMarkup command);

    @POST
    @Path("editMessageText")
    public Response<Message> editMessageText(EditMessageText command);
}
