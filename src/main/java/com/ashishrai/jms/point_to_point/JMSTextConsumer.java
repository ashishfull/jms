
package com.ashishrai.jms.point_to_point;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSTextConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMSTextConsumer.class);

	public static void main(String[] args) {

		try {
			// Set up JNDI properties
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			props.put(Context.PROVIDER_URL, "t3://localhost:7001");
			// Get JNDI context
			Context context = new InitialContext(props);
			// Lookup connection factory and queue
			ConnectionFactory factory = (ConnectionFactory) context.lookup("MY_JMS_CF");
			Destination queue = (Destination) context.lookup("MY_JMS_QUEUE");
			// Try-with-resources for automatic cleanup
			try (Connection connection = factory.createConnection();
			    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			    MessageConsumer consumer = session.createConsumer(queue)) {
				// Start connection to begin receiving messages
				connection.start();
				// Receive message
				LOGGER.info("Waiting for messages...");
				Message message = consumer.receive();
				// Check if it's a TextMessage and process it
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					LOGGER.info("Received TextMessage: " + text);
				} else {
					LOGGER.info("Received non-TextMessage: " + message);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
