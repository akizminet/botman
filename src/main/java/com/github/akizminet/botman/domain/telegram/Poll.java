package com.github.akizminet.botman.domain.telegram;

import java.util.List;

public record Poll (
    String id,
    String question,
    List<MessageEntity> questionEntities,
    List<PollOption> options,
    int totalVoterCount,
    Boolean isClosed,
    Boolean isAnonymous,
    String type,
    Boolean allowMultipleAnswers
) {

}
