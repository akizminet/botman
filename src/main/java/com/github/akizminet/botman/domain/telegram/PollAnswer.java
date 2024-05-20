package com.github.akizminet.botman.domain.telegram;

public record PollAnswer(
    String pollId,
    java.util.Optional<Chat> voterChat,
    User user,
    java.util.List<Integer> optionIds
) {
    
}
