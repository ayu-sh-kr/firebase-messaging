package dev.archimedes.firebasemessaging;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/fms")
@RequiredArgsConstructor
public class FMSController {


  private final MessageUsingTokenUseCase messageUsingTokenUseCase;
  private final MessageUsingTopicUseCase messageUsingTopicUseCase;

  @PostMapping("/v1/singleton")
  @ResponseStatus(HttpStatus.OK)
  void send(@RequestBody SingleMessageRequest request) throws FirebaseMessagingException {
    messageUsingTokenUseCase.execute(request);
  }

  @PostMapping("/v1/broadcast")
  @ResponseStatus(HttpStatus.OK)
  void send(@RequestBody BatchMessageRequest request) throws FirebaseMessagingException {
    messageUsingTokenUseCase.execute(request);
  }

  @PostMapping("/v1/topic")
  @ResponseStatus(HttpStatus.OK)
  void send(@RequestBody TopicMessageRequest request) throws FirebaseMessagingException {
    messageUsingTopicUseCase.execute(request);
  }


  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(FirebaseMessagingException.class)
  void handler(FirebaseMessagingException exception) {
    log.info(exception.getMessage());
  }

}
