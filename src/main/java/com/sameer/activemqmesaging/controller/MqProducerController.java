package com.sameer.activemqmesaging.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/publish")
public class MqProducerController {

  @Autowired
  private Queue queue;

  @Autowired
  private JmsTemplate jmsTemplate;

  @Autowired
  ActiveMQConnectionFactory activeMQConnectionFactory;

  @GetMapping("{message}")
  public String publishMessage(@PathVariable String message) throws JMSException {

    Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
    Connection connection = activeMQConnectionFactory.createConnection();
    connection.start();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    for (int i = 0; i < 100; i++) {
      linkedHashMap.put("some" + i, i);

    }
    jmsTemplate.convertAndSend(queue, linkedHashMap);


//    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//    // Create Destination queue
//    Destination queue = session.createQueue("Queue");
//
//    // Create a producer
//    MessageProducer producer = session.createProducer(queue);
//
//    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//
//
//    Message message1 = session.createTextMessage("<foo>hey</foo>");
//    message1.setStringProperty("JMSXGroupID", "IBM_NASDAQ_20/4/05");
//    producer.send(message1);

    return "Saved to the queue !";
  }
}
