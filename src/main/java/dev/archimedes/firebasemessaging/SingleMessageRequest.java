package dev.archimedes.firebasemessaging;

public record SingleMessageRequest(
    String token,
    String title,
    String message
) {
}
