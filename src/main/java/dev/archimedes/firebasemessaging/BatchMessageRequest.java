package dev.archimedes.firebasemessaging;

import java.util.List;

public record BatchMessageRequest(
    List<String> tokens,
    String title,
    String message
) {
}
