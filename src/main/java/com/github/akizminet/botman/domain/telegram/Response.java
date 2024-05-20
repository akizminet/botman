package com.github.akizminet.botman.domain.telegram;

public record Response<T>(Boolean ok, T result) {
}
