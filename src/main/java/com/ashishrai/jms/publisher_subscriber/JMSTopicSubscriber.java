
package com.ashishrai.jms.publisher_subscriber;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSTopicSubscriber {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMSTopicSubscriber.class);

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
			    MessageConsumer consumer = session.createConsumer(topic)) {
				// Start the connection to begin receiving messages
				connection.start();
				// Receive message
				LOGGER.info("Waiting for messages from topic...");
				Message message = consumer.receive();
				if (message instanceof TextMessage textMessage) {
					LOGGER.info("Received message: " + textMessage.getText());
				} else {
					LOGGER.info("Received non-TextMessage: " + message);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
