package br.com.messagesactivemq.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerQueueService {
	
	@JmsListener(destination = "fila.pedidos", id = "listenerQueue1")
	public void onReceiverQueue(String message) {
		
		System.out.println(String.format("Listener 1 recebeu mensagem: %s", message));
	}
//	
//	@JmsListener(destination = "fila.pedidos")
//	public void onReceiverQueue2(String message) {
//		System.out.println(String.format("Listener 2 recebeu mensagem: %s", message));
//	}
	

}
