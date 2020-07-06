package com.catalog.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${as.rabbitmq.exchange}")
	String exchange;
	
	@Value("${as.rabbitmq.routingkey}")
	String routingKey;
	
	public void send(Object obj) {
		rabbitTemplate.convertAndSend(exchange,routingKey,obj);
		System.out.println("message sent to Queue...."+obj);
	}
	
}
