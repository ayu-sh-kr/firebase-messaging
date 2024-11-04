package dev.archimedes.firebasemessaging;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class MessageUsingTopicUseCase {

  private final FirebaseMessaging firebaseMessaging;


  void execute(TopicMessageRequest request) throws FirebaseMessagingException {
    Message message = Message.builder()
        .setNotification(
            Notification.builder()
                .setTitle(request.title())
                .setBody(request.message())
                .build()
        )
        .setTopic(request.topic())
        .build();

    String response = firebaseMessaging.send(message);
    log.info(response);
  }


}
