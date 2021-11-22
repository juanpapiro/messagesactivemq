package br.com.messagesactivemq.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String user;

    @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        if ( "".equals(user) ) {
            return new ActiveMQConnectionFactory(brokerUrl);
        }
        return new ActiveMQConnectionFactory(user, password, brokerUrl);
    }

//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory() {
//       return new CachingConnectionFactory(connectionFactory());
//    }
  
//    // O pool de conexoes pode ser utilizado, pois criar conexoes e algo custoso
//    @Bean
//    public PooledConnectionFactory pooledConnectionFactory() {
//       PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(connectionFactory());
//       pooledConnectionFactory.setMaxConnections(10);
//       return pooledConnectionFactory;
//    }
  
//    @Bean
//    public JmsTemplate jmsTemplate() {
//       JmsTemplate template = new JmsTemplate();
//       template.setConnectionFactory(cachingConnectionFactory());
//       template.setPubSubDomain(true);
//       return template;
//    }
//  
//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//       DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//       factory.setConnectionFactory(cachingConnectionFactory());
//       factory.setSessionTransacted(true);
//       factory.setSubscriptionDurable(true);
//       factory.setPubSubDomain(true);
//       return factory;
//    }
    
    @Bean
    public DefaultJmsListenerContainerFactory jmsFactoryTopic(ConnectionFactory connectionFactory,
                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        
        factory.setClientId("client1");
        factory.setSubscriptionDurable(true);
        factory.setSessionTransacted(true);
        return factory;
    }
    @Bean
    public DefaultJmsListenerContainerFactory jmsFactoryTopic2(ConnectionFactory connectionFactory,
    		DefaultJmsListenerContainerFactoryConfigurer configurer) {
    	DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    	configurer.configure(factory, connectionFactory);
    	factory.setPubSubDomain(true);
    	
    	factory.setClientId("client2");
    	factory.setSubscriptionDurable(true);
    	factory.setSessionTransacted(true);
    	return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplateTopic() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
 
}