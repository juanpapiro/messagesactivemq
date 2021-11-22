package br.com.messagesactivemq.controller;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.QosSettings;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/massagebroker")
public class ProducerTopicController {
	
	@Autowired
	private JmsTemplate jmsTemplateTopic;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
	@Value("${spring.activemq.topic-name}")
	private String topicoPedido;
	
	@Value("${spring.activemq.queue-name:}")
	private String filaFinanceiro;
	
	@PostMapping("/topic")
	public void sedMessage(@RequestBody String message, @RequestParam(required = false) String selector) {
//		jmsTemplateTopic.convertAndSend("topico.pedidos, topico.notas", message);
		
		jmsTemplateTopic.convertAndSend(topicoPedido, message, m -> {
			m.setStringProperty("pedido", selector);
			return m;
		});
	}
	
	@PostMapping("/queue")
	public void sedMessageQueue(@RequestBody String message, @RequestParam int priority) {
		QosSettings settings = new QosSettings();
		settings.setPriority(priority);
//		settings.setTimeToLive(120000);
//		settings.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		jmsTemplate.setPriority(priority);
		jmsTemplate.setQosSettings(settings);
		jmsTemplate.convertAndSend(filaFinanceiro, message + String.valueOf(priority));
	}
	

}
