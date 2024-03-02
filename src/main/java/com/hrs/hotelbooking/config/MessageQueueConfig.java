package com.hrs.hotelbooking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javax.jms.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * The config to message queue.
 *
 * @author datnguyenv1
 * @version 1.0
 * @since 2024/03/02
 */
@Configuration
public class MessageQueueConfig {

  /**
   * Create Jackson JmsMessage Converter.
   *
   * @return MessageConverter
   */
  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();

    ObjectMapper objectMapper =
        new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    messageConverter.setTargetType(MessageType.TEXT);
    messageConverter.setTypeIdPropertyName("_type");
    messageConverter.setObjectMapper(objectMapper);
    return messageConverter;
  }

  /**
   * Create jms Template.
   *
   * @param connectionFactory connectionFactory
   * @param jacksonJmsMessageConverter jacksonJmsMessageConverter
   * @return JmsTemplate
   */
  @Bean
  public JmsTemplate jmsTemplate(
      @Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory,
      MessageConverter jacksonJmsMessageConverter) {
    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setSessionTransacted(true);
    jmsTemplate.setMessageConverter(jacksonJmsMessageConverter);
    jmsTemplate.setConnectionFactory(connectionFactory);
    jmsTemplate.setPubSubDomain(true);
    return jmsTemplate;
  }

}