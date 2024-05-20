package com.github.akizminet.botman.domain.telegram;

public record Chat(
    int id,
    String type,
    String title,
    String userName,
    String firstName,
    String lastName,
    Boolean isForum
){}
