package com.github.akizminet.botman.domain.telegram;

public record User(long id, boolean isBot, String firstName, String lastName, String username, String languageCode) {
    public String fullName() {
        return String.format("%s %s",this.firstName, this.lastName);
    }
}
