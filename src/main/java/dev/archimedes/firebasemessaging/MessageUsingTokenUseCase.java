package dev.archimedes.firebasemessaging;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class MessageUsingTokenUseCase {

  private final FirebaseMessaging firebaseMessaging;


  void execute(SingleMessageRequest request) throws FirebaseMessagingException {
    Message message = Message.builder()
        .setToken(request.token())
        .setNotification(
            Notification.builder()
                .setTitle(request.title())
                .setBody(request.message())
                .build()
        )
        .build();

    String response = firebaseMessaging.send(message);
    log.info(response);
  }

  void execute(BatchMessageRequest request) throws FirebaseMessagingException {
    MulticastMessage message = MulticastMessage.builder()
        .setNotification(
            Notification.builder()
                .setTitle(request.title())
                .setBody(request.message())
                .build()
        )
        .addAllTokens(request.tokens())
        .build();

    BatchResponse batchResponse = firebaseMessaging.sendEachForMulticast(message);
    log.info("Failure count: {}", batchResponse.getFailureCount());
    log.info("Success count: {}", batchResponse.getSuccessCount());
  }

}
