package com.github.akizminet.botman.domain.telegram.methods;

import com.github.akizminet.botman.domain.telegram.InputPollOption;
import java.util.List;
import java.util.Optional;

public record SendPoll(
        int chatId,
        Optional<Integer> messageThreadId,
        String question,
        List<InputPollOption> options,
        Optional<String> questionParseMode,
        Boolean allowsMultipleAnswers,
        Boolean isAnonymous)
        implements Method {

    public SendPoll(int chatId, String question, List<InputPollOption> options, Boolean allowsMultipleAnswers) {
        this(chatId, Optional.empty(), question, options, Optional.empty(), allowsMultipleAnswers, false);
    }
}
