
package com.ashishrai.jms.point_to_point;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMSTextProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JMSTextProducer.class);

	public static void main(String[] args) {

		try {
			// Set up JNDI properties
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			props.put(Context.PROVIDER_URL, "t3://localhost:7001"); // WebLogic URL
			// Get JNDI context
			Context context = new InitialContext(props);
			// Lookup connection factory and queue
			ConnectionFactory factory = (ConnectionFactory) context.lookup("MY_JMS_CF");
			Destination queue = (Destination) context.lookup("MY_JMS_QUEUE");
			// Create connection, session, and producer, and conveniently close using try-with-resources
			try (Connection connection = factory.createConnection();
			    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			    MessageProducer producer = session.createProducer(queue)) {
				// Create and send a TextMessage
				String messageText = "Hello from JMS!";
				TextMessage textMessage = session.createTextMessage(messageText);
				producer.send(textMessage);
				LOGGER.info("TextMessage sent: " + messageText);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
