package com.github.akizminet.botman.domain.telegram;

import java.util.Optional;

public record Update(
    int updateId,
    Optional<Message> message,
    Optional<Message> editedMessage,
    Optional<Message> channelPost,
    Optional<Message> editedChannelPost,
    Optional<MessageReactionUpdated> messageReaction,
    Optional<MessageReactionCountUpdated> messageReactionCount,
    Optional<InlineQuery> inlineQuery,
    Optional<ChosenInlineResult> chosenInlineResult,
    Optional<CallbackQuery> callbackQuery,
    Optional<ShippingQuery> shippingQuery,
    Optional<PreCheckoutQuery> preCheckoutQuery,
    Optional<Poll> poll,
    Optional<PollAnswer> pollAnswer,
    Optional<ChatMemberUpdated> myChatMember,
    Optional<ChatMemberUpdated> chatMember,
    Optional<ChatJoinRequest> chatJoinRequest,
    Optional<ChatBoostUpdated> chatBoost,
    Optional<ChatBoostRemoved> removedChatBoost
) {}