package com.sameer.activemqmesaging.Consumer;

import java.util.HashMap;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer implements Runnable {

  @JmsListener(destination = "memory_queue")
  public void listen(HashMap<String, Integer> message) {
    System.out.println("Got the message as : " + message);
    // run();
  }

  @Override
  public void run() {
    try {
      ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

      //Create Connection
      Connection connection = factory.createConnection();

      // Start the connection
      connection.start();

      // Create Session
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

      //Create queue
      Destination queue = session.createQueue("Queue");

      MessageConsumer consumer = session.createConsumer(queue);

      Message message = consumer.receive(1000);

      if (message instanceof TextMessage) {
        TextMessage textMessage = (TextMessage) message;
        String text = textMessage.getText();
        System.out.println("Consumer Received: " + text);
      }

      session.close();
      connection.close();
    } catch (Exception ex) {
      System.out.println("Exception Occured");
    }
  }
}
