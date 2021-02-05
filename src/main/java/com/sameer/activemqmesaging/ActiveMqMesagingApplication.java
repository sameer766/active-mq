package com.sameer.activemqmesaging;

import javax.jms.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.util.ErrorHandler;

@SpringBootApplication
@Slf4j
public class ActiveMqMesagingApplication {

  public static void main(String[] args) {
    SpringApplication.run(ActiveMqMesagingApplication.class, args);
  }

  @Bean
  JmsListenerContainerFactory<?> jmsContainerFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

    // anonymous class
    factory.setErrorHandler(
        new ErrorHandler() {
          @Override
          public void handleError(Throwable t) {
            log.warn("An error has occurred in the transaction", t);
          }
        });

    // lambda function
    factory.setErrorHandler(t -> log.warn("An error has occurred in the transaction", t));

    configurer.configure(factory, connectionFactory);
    return factory;
  }

}
