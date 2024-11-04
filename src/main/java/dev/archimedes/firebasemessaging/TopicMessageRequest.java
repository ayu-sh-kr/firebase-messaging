package dev.archimedes.firebasemessaging;

public record TopicMessageRequest(
    String topic,
    String title,
    String message
) {
}
