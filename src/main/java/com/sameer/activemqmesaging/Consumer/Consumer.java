package com.sameer.activemqmesaging.Consumer;

import com.sameer.activemqmesaging.UserData;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer implements MessageListener {

  @Override
  @JmsListener(destination = "memory_queue")
  public void onMessage(Message message) {
    try {
      ObjectMessage objectMessage = (ObjectMessage) message;
      UserData employee = (UserData) objectMessage.getObject();
      //do additional processing
      log.info("Received Message: " + employee);
    } catch (Exception e) {
      log.error("Received Exception : " + e);
    }
  }
}
