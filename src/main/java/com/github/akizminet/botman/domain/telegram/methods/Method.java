package com.github.akizminet.botman.domain.telegram.methods;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "method")
@JsonSubTypes({
    @Type(value = SendMessage.class, name = "sendMessage"),
    @Type(value = SendPoll.class, name = "sendPoll"),
    @Type(value = AnswerCallbackQuery.class, name = "answerCallbackQuery"),
    @Type(value = EditMessageText.class, name = "editMessageReplyMarkup"),
    @Type(value = EditMessageText.class, name = "editMessageText")
})
public interface Method {}
