package com.hrs.hotelbooking.messaging;

import java.io.IOException;

/**
 * MessageConsumer abstract class provide abstract for consuming message layer.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
public interface MessageConsumer<T> {

  /**
   * Receive a message from the message queue.
   *
   * @param message the message to receive
   */
  void receive(T message);

}
