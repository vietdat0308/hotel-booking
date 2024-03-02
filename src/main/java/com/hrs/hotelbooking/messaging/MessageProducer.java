package com.hrs.hotelbooking.messaging;

import javax.jms.Destination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * MessageProducer class supports send jms message.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MessageProducer {

  private final JmsTemplate jmsTemplate;

  public <T> void send(String queueName, final T message) {

    Destination destination = new ActiveMQQueue(queueName);
    try {
      jmsTemplate.convertAndSend(destination, message);
    } catch (JmsException jmsException) {
      throw new RuntimeException("Error when sending message");
    }
    log.info("Message {} sent", message);
  }
}
