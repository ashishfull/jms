
package com.ashishrai.jms.publisher_subscriber;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSTopicPublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMSTopicPublisher.class);

	public static void main(String[] args) {

		try {
			// Set up JNDI properties
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			props.put(Context.PROVIDER_URL, "t3://localhost:7001"); // Replace with your WebLogic URL
			// Get JNDI context
			Context context = new InitialContext(props);
			// Lookup connection factory and topic
			ConnectionFactory factory = (ConnectionFactory) context.lookup("MY_JMS_CF");
			Topic topic = (Topic) context.lookup("MY_JMS_TOPIC");
			// Try-with-resources for automatic resource cleanup
			try (Connection connection = factory.createConnection();
			    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			    MessageProducer producer = session.createProducer(topic)) {
				// Create and send a message
				String messageText = "Hello Subscribers!";
				TextMessage message = session.createTextMessage(messageText);
				producer.send(message);
				LOGGER.info("Message sent to topic: " + messageText);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
