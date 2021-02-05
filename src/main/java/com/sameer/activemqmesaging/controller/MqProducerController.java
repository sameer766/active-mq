package com.sameer.activemqmesaging.controller;

import com.sameer.activemqmesaging.UserData;
import javax.jms.JMSException;
import javax.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/publish")
public class MqProducerController {

  @Autowired
  private Queue queue;

  @Autowired
  private JmsTemplate jmsTemplate;

  @PostMapping(path = "/data", consumes = "application/json", produces = "application/json")
  public String publishMessage(@RequestBody UserData user) throws JMSException {
    jmsTemplate.convertAndSend(queue, user);
    return "Saved to the queue !";
  }
}
