package br.com.messagesactivemq.service;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerTopicService {

	
	@JmsListener(
			destination = "${spring.activemq.topic-name}",
			containerFactory = "jmsFactoryTopic",
			id = "listener1",
			subscription = "listener1")
	public void onReceiverTopic(String message) throws Exception {
		System.out.println(String.format("Listener 1 recebeu mensagem: %s", message));
//		throw new Exception();
	}
	
	@JmsListener(
			destination = "${spring.activemq.topic-name}",
			containerFactory = "jmsFactoryTopic2",
			id = "listener2",
			subscription = "listener2",
			selector = "pedido = 'novo'")
	public void onReceiverTopic2(String message) {
		System.out.println(String.format("Listener 2 recebeu mensagem: %s", message));
	}
	
	
}
